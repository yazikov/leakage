package ru.rushydro.vniig.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rushydro.vniig.dao.AbstractDAO;
import ru.rushydro.vniig.dao.PassportParamSysDAO;
import ru.rushydro.vniig.entry.PassportParamSys;
import ru.rushydro.vniig.entry.SignSys;
import ru.rushydro.vniig.model.Page;
import ru.rushydro.vniig.storage.entry.PassportParamSysStorage;
import ru.rushydro.vniig.storage.entry.SignSysStorage;
import ru.rushydro.vniig.util.data.DateConverter;

import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by alyon on 18.10.2015.
 */
@Component
public class SignSysStorageDAO extends AbstractStorageDAO<SignSysStorage>{

    @Autowired
    PassportParamSysDAO passportParamSysDAO;

    @Autowired
    TypeSignalTblStorageDAO typeSignalTableDao;

    public SignSysStorage getById(Integer id) {
        TypedQuery<SignSysStorage> query = em.createQuery("SELECT mps FROM SignSysStorage mps WHERE mps.passportParamSys.idSensors = :id order by mps.idSign desc ", SignSysStorage.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public SignSysStorage save(SignSysStorage signSys) {
        try {
//            em.getTransaction().begin();
            if (signSys.getPassportParamSys() == null || signSys.getPassportParamSys().getIdSensors() == null) {
                em.persist(signSys);
            } else {
                em.merge(signSys);
            }
            em.flush();
//            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signSys;
    }

    public void insertValues(Integer id, Double value)
    {
        PassportParamSys sensor = passportParamSysDAO.getById(id);

        boolean isRelease = sensor.getIs_release();

        Float ustavkaPre = isRelease ? sensor.getCriter_release().floatValue() : sensor.getCriterion().floatValue();
        Float ustavkaAv = Float.MAX_VALUE;

        int val = 1;
        if(value < ustavkaPre)
            val = 1;
        else if(value > ustavkaPre && value < ustavkaAv)
            val = 2;
        else if(value > ustavkaAv)
            val = 3;

        int rowCount = getJdbcTemplate().update("insert into SIGN_SYS(sort_sign, id_sensors, date_sign, time_sign) VALUES(?,?,CURRENT_DATE,CURRENT_TIME)", val, id);

//        return getById(id);
    }

    public Page<SignSysStorage> filter(String startDateStr, String endDateStr, String type, String signal, Long page, Integer pageSize) {
        List<Integer> signals = null;
        List<String> types = null;
        Date startDate = null;
        Date endDate = null;
        if (type != null && !type.isEmpty()) {
            if (type.contains(",")) {
                types = Arrays.asList(type.split(","));
            } else {
                types = new ArrayList<>();
                types.add(type);
            }
        }
        if (signal != null && !signal.isEmpty()) {
            if (signal.contains(",")) {
                signals = Arrays.asList(signal.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
            } else {
                signals = new ArrayList<>();
                signals.add(Integer.parseInt(signal));
            }
        }
        if (startDateStr != null && !startDateStr.isEmpty()) {
            try {
                startDate = DateConverter.parse(startDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            try {
                endDate = DateConverter.parse(endDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        StringBuilder where = new StringBuilder();
        if (startDate != null && endDate != null) {
            where.append(" where ");
            where.append(" sss.dateSign between :startDate and :endDate ");
        } else if (startDate != null) {
            where.append(" where ");
            where.append(" sss.dateSign >= :startDate ");
        } else if (endDate != null) {
            where.append(" where ");
            where.append(" sss.dateSign <= :endDate ");
        }

        if (types != null) {
            if (where.toString().isEmpty()) {
                where.append(" where ");
            } else {
                where.append(" and ");
            }
            where.append(" sss.passportParamSys.objMonitor in :types ");
        }

        if (signals != null) {
            if (where.toString().isEmpty()) {
                where.append(" where ");
            } else {
                where.append(" and ");
            }
            where.append(" sss.sortSign.idSignal in :signals ");
        }


        TypedQuery<SignSysStorage> query = em.createQuery("SELECT sss FROM SignSysStorage sss " + where, SignSysStorage.class);
        query.setFirstResult((page.intValue() - 1) * pageSize);
        query.setMaxResults(pageSize);

        TypedQuery<Long> countQuery = em.createQuery("SELECT count(sss) FROM SignSysStorage sss " + where, Long.class);

        if (startDate != null) {
            query.setParameter("startDate", startDate);
            countQuery.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
            countQuery.setParameter("endDate", endDate);
        }
        if (types != null) {
            query.setParameter("types", types);
            countQuery.setParameter("types", types);
        }
        if (signals != null) {
            query.setParameter("signals", signals);
            countQuery.setParameter("signals", signals);
        }

        return new Page<>(page, pageSize, countQuery.getSingleResult(), query.getResultList());

    }


//    public boolean kventSensor(Integer id) {
//        SignSys signSys = getById(id);
//        signSys.setTimeKvint(new Date());
//        signSys.setDateKvint(new Date());
//        return true;
//    }
}

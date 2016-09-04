package ru.rushydro.vniig.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rushydro.vniig.dao.AbstractDAO;
import ru.rushydro.vniig.dao.MeasParamSysDAO;
import ru.rushydro.vniig.entry.MeasParamSys;
import ru.rushydro.vniig.model.Page;
import ru.rushydro.vniig.storage.entry.MeasParamSysStorage;
import ru.rushydro.vniig.storage.entry.SignSysStorage;
import ru.rushydro.vniig.util.data.DateConverter;

import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nikolay on 11.10.15.
 */
@Component
public class MeasParamSysStorageDAO extends AbstractStorageDAO<MeasParamSysStorage> {

    @Autowired
    MeasParamSysDAO measParamSysDAO;

    public List<MeasParamSysStorage> getById(Integer id) {
        TypedQuery<MeasParamSysStorage> query = em.createQuery("SELECT mps FROM MeasParamSysStorage mps WHERE mps.passportParamSys.idSensors = :id ", MeasParamSysStorage.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getResultList();
    }

    public MeasParamSysStorage getOneById(Integer id) {
        TypedQuery<MeasParamSysStorage> query = em.createQuery("SELECT mps FROM MeasParamSysStorage mps WHERE mps.passportParamSys.idSensors = :id order by mps.dateMeas desc, mps.timeMeas desc", MeasParamSysStorage.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public MeasParamSysStorage save(MeasParamSysStorage measParamSys) {
        try {
//            em.getTransaction().begin();
            if (measParamSys.getId() == null) {
                em.persist(measParamSys);
            } else {
                em.merge(measParamSys);
            }
            em.flush();
//            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return measParamSys;
    }

    public void insertValue (Integer id, Double value) {
        MeasParamSys measParamSys = measParamSysDAO.getById(id);
        getJdbcTemplate().update("INSERT into meas_param_sys(id_sensors, status_sensors, date_meas, time_meas, value_meas, relative_value_meas, trust_meas, work_sensors) VALUES(?,?,CURRENT_DATE,CURRENT_TIME,?,?,?,?) ",
                id, measParamSys.getStatusSensors() , value, measParamSys.getRelativeValueMeas(), measParamSys.getTrustMeas(), measParamSys.getWorkSensors());

    }

    public List<MeasParamSysStorage> filter(String startDateStr, String endDateStr, List<Integer> sensors) {
        Date startDate = null;
        Date endDate = null;
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
            where.append(" mpss.dateMeas between :startDate and :endDate ");
        } else if (startDate != null) {
            where.append(" where ");
            where.append(" mpss.dateMeas >= :startDate ");
        } else if (endDate != null) {
            where.append(" where ");
            where.append(" mpss.dateMeas <= :endDate ");
        }

        if (sensors != null && !sensors.isEmpty()) {
            if (where.toString().isEmpty()) {
                where.append(" where ");
            } else {
                where.append(" and ");
            }
            where.append(" mpss.passportParamSys.idSensors in :sensors ");
        }


        TypedQuery<MeasParamSysStorage> query = em.createQuery("SELECT mpss FROM MeasParamSysStorage mpss " + where, MeasParamSysStorage.class);

        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }
        if (sensors != null && !sensors.isEmpty()) {
            query.setParameter("sensors", sensors);
        }

        return query.getResultList();

    }

    public boolean onSensor(Integer id) {
        MeasParamSysStorage measParamSys = getOneById(id);
        measParamSys.setWorkSensors(true);
        return save(measParamSys).getWorkSensors();
    }

    public boolean offSensor(Integer id) {
        MeasParamSysStorage measParamSys = getOneById(id);
        measParamSys.setWorkSensors(false);
        return save(measParamSys).getWorkSensors();
    }

    public boolean insertLevel(double level) {
        MeasParamSys measParamSys = measParamSysDAO.getById(61);
        return getJdbcTemplate().update("INSERT into meas_param_sys(id_sensors, status_sensors, date_meas, time_meas, value_meas, relative_value_meas, trust_meas, work_sensors) VALUES(?,?,CURRENT_DATE,CURRENT_TIME,?,?,?,?) ",
                61, measParamSys.getStatusSensors() , level, measParamSys.getRelativeValueMeas(), measParamSys.getTrustMeas(), measParamSys.getWorkSensors()) > 0;
    }
}

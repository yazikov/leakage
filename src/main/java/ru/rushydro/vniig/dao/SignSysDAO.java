package ru.rushydro.vniig.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import ru.rushydro.vniig.entry.SignSys;

import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.util.Date;

/**
 * Created by alyon on 18.10.2015.
 */
@Component
public class SignSysDAO extends AbstractDAO<SignSys>{


    @Autowired
    TypeSignalTblDAO typeSignalTableDao;

    public SignSys getById(Integer id) {
        TypedQuery<SignSys> query = em.createQuery("SELECT mps FROM SignSys mps WHERE mps.passportParamSys.idSensors = :id ", SignSys.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    @Transactional
    public SignSys save(SignSys signSys) {
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

    public SignSys updateValues(Integer id, Double value)
    {
        SignSys signSys = getById(id);
        boolean isRelease = signSys.getPassportParamSys().getIs_release();

        Float ustavkaPre = isRelease ? signSys.getPassportParamSys().getCriter_release().floatValue() : signSys.getPassportParamSys().getCriterion().floatValue();
        Float ustavkaAv = Float.MAX_VALUE;
        int val = 1;
        if(value < ustavkaPre) {
            val = 1;
            isRelease = false;
        } else if(value > ustavkaPre && value < ustavkaAv) {
            val = 2;
            isRelease = true;
        } else if(value > ustavkaAv)
            val = 3;

        int rowCount = getJdbcTemplate().update("update PASSPORT_PARAM_SYS set IS_RELEASE = ? where id_sensors = ?", isRelease, id);
        System.out.println("Row count: " + rowCount);
        rowCount = getJdbcTemplate().update("update SIGN_SYS set sort_sign = ?, date_sign = CURRENT_DATE, time_sign = CURRENT_TIME, date_kvint = null, time_kvint = null where id_sensors = ?", val, id);
        System.out.println("Row count: " + rowCount);
        return getById(id);
    }



    public boolean kventSensor(Integer id) {
        SignSys signSys = getById(id);
        signSys.setTimeKvint(new Date());
        signSys.setDateKvint(new Date());
        return true;
    }
}

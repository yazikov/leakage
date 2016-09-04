package ru.rushydro.vniig.dao;

import org.springframework.stereotype.Component;
import ru.rushydro.vniig.entry.PassportParamSys;
import ru.rushydro.vniig.model.Page;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alyon on 27.09.2015.
 */
@Component
public class PassportParamSysDAO extends AbstractDAO<PassportParamSys> {

    public List<String> getAllRootNodes() {
        TypedQuery<String> query = em.createQuery("SELECT DISTINCT pps.objMonitor FROM PassportParamSys pps WHERE pps.number != '' order by pps.objMonitor ", String.class);

        return query.getResultList();
    }

    public List<String> getRootNodesByInsision(Integer insision) {
        TypedQuery<String> query = em.createQuery("SELECT DISTINCT pps.objMonitor FROM PassportParamSys pps JOIN pps.insisionSensorsList ins WHERE ins.insision = :insision and pps.number != ''  order by pps.objMonitor ", String.class);
        query.setParameter("insision", insision);
        return query.getResultList();
    }

    public List<PassportParamSys> getSensorByType(Integer type) {
        TypedQuery<PassportParamSys> query = em.createQuery("SELECT pps FROM PassportParamSys pps WHERE pps.measParamTypeSig.idUstavka = :measParamType ", PassportParamSys.class);
        query.setParameter("measParamType",type);
        return query.getResultList();
    }

    public List<PassportParamSys> getSensors() {
        TypedQuery<PassportParamSys> query = em.createQuery("SELECT pps FROM PassportParamSys pps ", PassportParamSys.class);
        return query.getResultList();
    }

    public Page<PassportParamSys> getSensorPageByType(int type, Long page, Integer pageSize) {
        TypedQuery<PassportParamSys> query = em.createQuery("SELECT pps FROM PassportParamSys pps WHERE pps.measParamTypeSig.idUstavka = :measParamType ", PassportParamSys.class);
        query.setParameter("measParamType",type);
        query.setFirstResult((page.intValue() - 1) * pageSize);
        query.setMaxResults(pageSize);

        TypedQuery<Long> countQuery = em.createQuery("SELECT count(pps) FROM PassportParamSys pps WHERE pps.measParamTypeSig.idUstavka = :measParamType ", Long.class);
        countQuery.setParameter("measParamType",type);

        return new Page<>(page, pageSize, countQuery.getSingleResult(), query.getResultList());
    }

    public List<PassportParamSys> getSensorByTypeAndInsision(Integer type, Integer insision) {
        TypedQuery<PassportParamSys> query = em.createQuery("SELECT pps FROM PassportParamSys pps JOIN pps.insisionSensorsList ins WHERE ins.insision = :insision and pps.measParamTypeSig.idUstavka = :measParamType ", PassportParamSys.class);
        query.setParameter("measParamType",type);
        query.setParameter("insision", insision);
        return query.getResultList();
    }

    public PassportParamSys getById(Integer id) {
        TypedQuery<PassportParamSys> query = em.createQuery("SELECT pps FROM PassportParamSys pps WHERE pps.idSensors = :id ", PassportParamSys.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }


    public PassportParamSys save(PassportParamSys passportParamSys) {
        try {
//            em.getTransaction().begin();
            if (passportParamSys.getIdSensors() == null) {
                em.persist(passportParamSys);
            } else {
                em.merge(passportParamSys);
            }
            em.flush();
//            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passportParamSys;
    }

    public Collection getAvandPreSign() throws SQLException
    {
        return null;
    }

    public Object getSensorByTypeUpper(int type) {
        TypedQuery<PassportParamSys> query = em.createQuery("SELECT pps FROM PassportParamSys pps WHERE pps.measParamTypeSig.idUstavka >= :measParamType ", PassportParamSys.class);
        query.setParameter("measParamType",type);
        return query.getResultList();
    }

    public Integer getLastId() {
        TypedQuery<Integer> query = em.createQuery("SELECT MAX(pps.idSensors) + 1 FROM PassportParamSys pps", Integer.class);
        return query.getSingleResult();
    }
}
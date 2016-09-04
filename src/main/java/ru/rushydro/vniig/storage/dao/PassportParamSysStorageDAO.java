package ru.rushydro.vniig.storage.dao;

import org.springframework.stereotype.Component;
import ru.rushydro.vniig.dao.AbstractDAO;
import ru.rushydro.vniig.entry.PassportParamSys;
import ru.rushydro.vniig.model.Page;
import ru.rushydro.vniig.storage.entry.PassportParamSysStorage;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by alyon on 27.09.2015.
 */
@Component
public class PassportParamSysStorageDAO extends AbstractStorageDAO<PassportParamSysStorage> {

    public List<String> getAllRootNodes() {
        TypedQuery<String> query = em.createQuery("SELECT DISTINCT pps.objMonitor FROM PassportParamSysStorage pps WHERE pps.number != '' order by pps.objMonitor ", String.class);

        return query.getResultList();
    }

    public List<String> getRootNodesByInsision(Integer insision) {
        TypedQuery<String> query = em.createQuery("SELECT DISTINCT pps.objMonitor FROM PassportParamSysStorage pps JOIN pps.insisionSensorsList ins WHERE ins.insision = :insision and pps.number != ''  order by pps.objMonitor ", String.class);
        query.setParameter("insision", insision);
        return query.getResultList();
    }

    public List<PassportParamSysStorage> getSensorByType(Integer type) {
        TypedQuery<PassportParamSysStorage> query = em.createQuery("SELECT pps FROM PassportParamSysStorage pps WHERE pps.measParamTypeSig.idUstavka = :measParamType ", PassportParamSysStorage.class);
        query.setParameter("measParamType",type);
        return query.getResultList();
    }

    public Page<PassportParamSysStorage> getSensorPageByType(int type, Long page, Integer pageSize) {
        TypedQuery<PassportParamSysStorage> query = em.createQuery("SELECT pps FROM PassportParamSysStorage pps WHERE pps.measParamTypeSig.idUstavka = :measParamType ", PassportParamSysStorage.class);
        query.setParameter("measParamType",type);
        query.setFirstResult((page.intValue() - 1) * pageSize);
        query.setMaxResults(pageSize);

        TypedQuery<Long> countQuery = em.createQuery("SELECT count(pps) FROM PassportParamSysStorage pps WHERE pps.measParamTypeSig.idUstavka = :measParamType ", Long.class);
        countQuery.setParameter("measParamType",type);

        return new Page<>(page, pageSize, countQuery.getSingleResult(), query.getResultList());
    }

    public List<PassportParamSysStorage> getSensorByTypeAndInsision(Integer type, Integer insision) {
        TypedQuery<PassportParamSysStorage> query = em.createQuery("SELECT pps FROM PassportParamSysStorage pps JOIN pps.insisionSensorsList ins WHERE ins.insision = :insision and pps.measParamTypeSig.idUstavka = :measParamType ", PassportParamSysStorage.class);
        query.setParameter("measParamType",type);
        query.setParameter("insision", insision);
        return query.getResultList();
    }

    public PassportParamSysStorage getById(Integer id) {
        TypedQuery<PassportParamSysStorage> query = em.createQuery("SELECT pps FROM PassportParamSysStorage pps WHERE pps.idSensors = :id ", PassportParamSysStorage.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public PassportParamSysStorage save(PassportParamSysStorage passportParamSys) {
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



}
package ru.rushydro.vniig.storage.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rushydro.vniig.dao.AbstractDAO;
import ru.rushydro.vniig.entry.UstavkaParamSys;
import ru.rushydro.vniig.storage.entry.UstavkaParamSysStorage;

import javax.persistence.TypedQuery;

/**
 * Created by nikolay on 21.02.16.
 */
@Component
public class UstavkaParamSysStorageDAO extends AbstractStorageDAO<UstavkaParamSysStorage> {

    public UstavkaParamSysStorage getById(Integer id) {
        TypedQuery<UstavkaParamSysStorage> query = em.createQuery("SELECT ups FROM UstavkaParamSysStorage ups WHERE ups.idUstavka = :id ", UstavkaParamSysStorage.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public UstavkaParamSysStorage save(UstavkaParamSysStorage ustavkaParamSys) {
        try {
//            em.getTransaction().begin();
            if (ustavkaParamSys.getIdUstavka() == null) {
                em.persist(ustavkaParamSys);
            } else {
                em.merge(ustavkaParamSys);
            }
            em.flush();
//            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ustavkaParamSys;
    }
}

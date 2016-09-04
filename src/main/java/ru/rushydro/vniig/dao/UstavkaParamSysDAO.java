package ru.rushydro.vniig.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rushydro.vniig.entry.MeasParamTypeSig;
import ru.rushydro.vniig.entry.UstavkaParamSys;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by nikolay on 21.02.16.
 */
@Component
public class UstavkaParamSysDAO extends AbstractDAO<UstavkaParamSys> {

    public UstavkaParamSys getById(Integer id) {
        TypedQuery<UstavkaParamSys> query = em.createQuery("SELECT ups FROM UstavkaParamSys ups WHERE ups.idUstavka = :id ", UstavkaParamSys.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public UstavkaParamSys save(UstavkaParamSys ustavkaParamSys) {
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

    public List<UstavkaParamSys> getComboItems() {
        TypedQuery<UstavkaParamSys> query = em.createQuery("SELECT ups FROM UstavkaParamSys ups WHERE ups.idUstavka > 3 ", UstavkaParamSys.class);
        return query.getResultList();
    }
}

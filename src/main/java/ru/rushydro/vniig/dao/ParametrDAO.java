package ru.rushydro.vniig.dao;

import org.springframework.stereotype.Component;
import ru.rushydro.vniig.entry.Parametr;

import javax.persistence.TypedQuery;

/**
 * Created by nikolay on 27.12.15.
 */
@Component
public class ParametrDAO extends AbstractDAO<Parametr> {

    public Parametr findById(String id) {
        TypedQuery<Parametr> query = em.createQuery("SELECT p FROM Parametr p WHERE lower(p.id) = lower(:id) ", Parametr.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public Parametr save(Parametr parametr) {
        try {
            em.getTransaction().begin();
            em.merge(parametr);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parametr;
    }

    public void update(String id, Long value) {
        getJdbcTemplate().update("update parameters set value = ? where lower(id) = lower(?)", value, id);
    }

}

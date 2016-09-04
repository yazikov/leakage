package ru.rushydro.vniig.dao;

import org.springframework.stereotype.Component;
import ru.rushydro.vniig.dao.AbstractDAO;
import ru.rushydro.vniig.entry.TypeSignalTable;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by alyon on 18.10.2015.
 */
@Component
public class TypeSignalTblDAO extends AbstractDAO<TypeSignalTable> {

    public TypeSignalTable getById(Integer id) {

        TypedQuery<TypeSignalTable> query = em.createQuery("SELECT mps FROM TypeSignalTable mps WHERE mps.idSignal = :id ", TypeSignalTable.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<TypeSignalTable> getAll() {
        TypedQuery<TypeSignalTable> query = em.createQuery("SELECT mps FROM TypeSignalTable mps ", TypeSignalTable.class);
        return query.getResultList();
    }
}

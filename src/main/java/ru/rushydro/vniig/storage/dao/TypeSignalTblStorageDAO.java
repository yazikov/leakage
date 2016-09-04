package ru.rushydro.vniig.storage.dao;

import org.springframework.stereotype.Component;
import ru.rushydro.vniig.dao.AbstractDAO;
import ru.rushydro.vniig.entry.TypeSignalTable;
import ru.rushydro.vniig.storage.entry.TypeSignalTableStorage;

import javax.persistence.TypedQuery;

/**
 * Created by alyon on 18.10.2015.
 */
@Component
public class TypeSignalTblStorageDAO extends AbstractStorageDAO<TypeSignalTableStorage> {

    public TypeSignalTableStorage getById(Integer id) {

        TypedQuery<TypeSignalTableStorage> query = em.createQuery("SELECT mps FROM TypeSignalTableStorage mps WHERE mps.idSignal = :id ", TypeSignalTableStorage.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}

package ru.rushydro.vniig.storage.dao;

import org.springframework.stereotype.Component;
import ru.rushydro.vniig.storage.entry.TypeSignalTableStorage;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by yazik on 27.02.2016.
 */
@Component
public class TypeSignalTableStorageDAO extends AbstractStorageDAO<TypeSignalTableStorage> {
    public TypeSignalTableStorage getById(Integer id) {

        TypedQuery<TypeSignalTableStorage> query = em.createQuery("SELECT mps FROM TypeSignalTableStorage mps WHERE mps.idSignal = :id ", TypeSignalTableStorage.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<TypeSignalTableStorage> getAll() {
        TypedQuery<TypeSignalTableStorage> query = em.createQuery("SELECT mps FROM TypeSignalTableStorage mps ", TypeSignalTableStorage.class);
        return query.getResultList();
    }
}

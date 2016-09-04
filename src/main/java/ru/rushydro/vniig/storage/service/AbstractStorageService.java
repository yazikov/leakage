package ru.rushydro.vniig.storage.service;

import ru.rushydro.vniig.dao.AbstractDAO;
import ru.rushydro.vniig.entry.AbstractEntry;
import ru.rushydro.vniig.storage.dao.AbstractStorageDAO;
import ru.rushydro.vniig.storage.entry.AbstractStorageEntry;

/**
 * Created by nikolay on 22.09.15.
 */
public class AbstractStorageService<T extends AbstractStorageEntry, S extends AbstractStorageDAO<T>> {
    S dao;

    public AbstractStorageService(S dao) {
        this.dao = dao;
    }

    public boolean delete(T object) {
        return dao.delete(object);
    }
}

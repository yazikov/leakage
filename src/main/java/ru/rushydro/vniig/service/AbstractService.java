package ru.rushydro.vniig.service;

import ru.rushydro.vniig.dao.AbstractDAO;
import ru.rushydro.vniig.entry.AbstractEntry;

/**
 * Created by nikolay on 22.09.15.
 */
public class AbstractService<T extends AbstractEntry, S extends AbstractDAO<T>> {
    S dao;

    public AbstractService(S dao) {
        this.dao = dao;
    }

    public boolean delete(T object) {
        return dao.delete(object);
    }
}

package ru.rushydro.vniig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.dao.ParametrDAO;
import ru.rushydro.vniig.entry.Parametr;

import javax.persistence.TypedQuery;

/**
 * Created by nikolay on 27.12.15.
 */
@Service
public class ParametrService extends AbstractService<Parametr, ParametrDAO> {

    @Autowired
    public ParametrService(ParametrDAO dao) {
        super(dao);
    }

    public Parametr findById(String id) {

        return dao.findById(id);
    }

    public Parametr save(Parametr parametr) {
        return dao.save(parametr);
    }

    public void update(String id, Long value) {
        dao.update(id, value);
    }
}

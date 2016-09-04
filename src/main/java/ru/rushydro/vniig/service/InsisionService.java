package ru.rushydro.vniig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.dao.InsisionDao;
import ru.rushydro.vniig.entry.Insision;

import java.util.List;

/**
 * Created by nikolay on 25.10.15.
 */
@Service
public class InsisionService extends AbstractService<Insision, InsisionDao> {

    @Autowired
    public InsisionService(InsisionDao dao) {
        super(dao);
    }

    public List<Insision> getAllInsision() {
        return dao.getAllInsision();
    }

}

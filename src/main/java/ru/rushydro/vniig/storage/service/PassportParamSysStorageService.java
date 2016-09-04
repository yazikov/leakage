package ru.rushydro.vniig.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.storage.dao.PassportParamSysStorageDAO;
import ru.rushydro.vniig.storage.entry.PassportParamSysStorage;

/**
 * Created by nikolay on 18.02.16.
 */
@Service
public class PassportParamSysStorageService extends AbstractStorageService<PassportParamSysStorage, PassportParamSysStorageDAO> {

    @Autowired
    public PassportParamSysStorageService(PassportParamSysStorageDAO dao) {
        super(dao);
    }

    public PassportParamSysStorage getById(Integer id) {
        return dao.getById(id);
    }

    public PassportParamSysStorage save(PassportParamSysStorage passportParamSysStorage) {
        return dao.save(passportParamSysStorage);
    }


}

package ru.rushydro.vniig.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.dao.UstavkaParamSysDAO;
import ru.rushydro.vniig.entry.UstavkaParamSys;
import ru.rushydro.vniig.service.AbstractService;
import ru.rushydro.vniig.storage.dao.UstavkaParamSysStorageDAO;
import ru.rushydro.vniig.storage.entry.UstavkaParamSysStorage;

/**
 * Created by yazik on 22.02.2016.
 */
@Service
public class UstavkaParamSysStorageService extends AbstractStorageService<UstavkaParamSysStorage, UstavkaParamSysStorageDAO> {

    @Autowired
    public UstavkaParamSysStorageService(UstavkaParamSysStorageDAO dao) {
        super(dao);
    }

    public UstavkaParamSysStorage getById(Integer id) {
        return dao.getById(id);
    }

    public UstavkaParamSysStorage save(UstavkaParamSysStorage ustavkaParamSys) {
        return dao.save(ustavkaParamSys);
    }
}

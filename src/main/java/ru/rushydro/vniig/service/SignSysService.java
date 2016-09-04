package ru.rushydro.vniig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.dao.SignSysDAO;
import ru.rushydro.vniig.entry.SignSys;

import javax.persistence.TypedQuery;
import java.util.Date;

/**
 * Created by nikolay on 25.10.15.
 */
@Service
public class SignSysService extends AbstractService<SignSys, SignSysDAO> {

    @Autowired
    public SignSysService(SignSysDAO dao) {
        super(dao);
    }

    public SignSys getById(Integer id) {
        return dao.getById(id);
    }

    public SignSys save(SignSys signSys) {
        return dao.save(signSys);
    }

    public SignSys updateValues(Integer id, Double value)
    {
        return dao.updateValues(id, value);
    }

    public boolean kventSensor (Integer id) {
        return dao.kventSensor(id);
    }
}

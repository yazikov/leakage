package ru.rushydro.vniig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.dao.InsisionSensorsDAO;
import ru.rushydro.vniig.entry.InsisionSensors;

import java.util.List;

/**
 * Created by nikolay on 25.10.15.
 */
@Service
public class InsisionSernsorsService extends AbstractService<InsisionSensors, InsisionSensorsDAO> {

    @Autowired
    public InsisionSernsorsService(InsisionSensorsDAO dao) {
        super(dao);
    }

    public List<InsisionSensors> getSensorsByIns(Integer ins) {
        return dao.getSensorsByIns(ins);
    }
}

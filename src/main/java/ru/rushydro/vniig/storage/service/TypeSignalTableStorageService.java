package ru.rushydro.vniig.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.storage.dao.TypeSignalTableStorageDAO;
import ru.rushydro.vniig.storage.entry.TypeSignalTableStorage;

/**
 * Created by yazik on 27.02.2016.
 */
@Service
public class TypeSignalTableStorageService extends AbstractStorageService<TypeSignalTableStorage, TypeSignalTableStorageDAO> {

    @Autowired
    public TypeSignalTableStorageService(TypeSignalTableStorageDAO dao) {
        super(dao);
    }

    public TypeSignalTableStorage getById(Integer id) {
        return dao.getById(id);
    }
}

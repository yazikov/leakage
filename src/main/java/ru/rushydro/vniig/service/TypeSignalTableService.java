package ru.rushydro.vniig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rushydro.vniig.dao.TypeSignalTblDAO;
import ru.rushydro.vniig.entry.TypeSignalTable;
import ru.rushydro.vniig.model.ComboItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 07.11.15.
 */
@Service
public class TypeSignalTableService extends AbstractService<TypeSignalTable, TypeSignalTblDAO> {

    @Autowired
    public TypeSignalTableService(TypeSignalTblDAO dao) {
        super(dao);
    }

    public List<TypeSignalTable> getAll() {
        return dao.getAll();
    }

    public TypeSignalTable getById(Integer id) {
        return dao.getById(id);
    }

    public List<ComboItem> getComboItems () {
        List<ComboItem> items = new ArrayList<>();
        for (TypeSignalTable type : getAll()) {
            ComboItem comboItem = new ComboItem();
            comboItem.setId(type.getIdSignal().toString());
            comboItem.setName(type.getTextSignal());
            items.add(comboItem);
        }
        return items;
    }
}

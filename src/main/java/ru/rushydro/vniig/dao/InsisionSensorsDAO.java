package ru.rushydro.vniig.dao;

import org.springframework.stereotype.Component;
import ru.rushydro.vniig.entry.InsisionSensors;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by alyon on 25.10.2015.
 */
@Component
public class InsisionSensorsDAO  extends AbstractDAO<InsisionSensors> {
    public List<InsisionSensors> getSensorsByIns(Integer ins) {
        TypedQuery<InsisionSensors> query = em.createQuery("SELECT ins FROM InsisionSensors ins WHERE ins.insision = :ins", InsisionSensors.class);
        query.setParameter("ins",ins);
        return query.getResultList();
    }
}

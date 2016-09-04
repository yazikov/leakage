package ru.rushydro.vniig.dao;

import org.springframework.stereotype.Component;
import ru.rushydro.vniig.entry.Insision;

import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Created by alyon on 18.10.2015.
 */
@Component
public class InsisionDao extends AbstractDAO<Insision>{
    public List<Insision> getAllInsision() {
        TypedQuery<Insision> query = em.createQuery("SELECT ins FROM Insision ins", Insision.class);
        return query.getResultList();
    }
}

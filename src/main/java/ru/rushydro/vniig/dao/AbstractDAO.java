package ru.rushydro.vniig.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.rushydro.vniig.entry.AbstractEntry;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by nikolay on 20.09.15.
 */
@Transactional("transactionManager")
public class AbstractDAO<T extends AbstractEntry> {


    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    JdbcTemplate jdbcTemplate;


    @PersistenceContext(unitName = "myEmf")
    @Qualifier("myEmf")
    EntityManager em;

    public AbstractDAO() {
    }

    public boolean delete(T object) {
        try {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public JdbcTemplate getJdbcTemplate () {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

}
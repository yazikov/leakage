package ru.rushydro.vniig.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.rushydro.vniig.entry.AbstractEntry;
import ru.rushydro.vniig.storage.entry.AbstractStorageEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 * Created by nikolay on 20.09.15.
 */
@Transactional("transactionManagerStorage")
public class AbstractStorageDAO<T extends AbstractStorageEntry> {


    @Autowired
    @Qualifier("dataSourceStorage")
    DataSource dataSource;

    JdbcTemplate jdbcTemplate;


    @PersistenceContext(unitName = "myEmfStorage")
    @Qualifier("myEmfStorage")
    EntityManager em;

    public AbstractStorageDAO() {
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
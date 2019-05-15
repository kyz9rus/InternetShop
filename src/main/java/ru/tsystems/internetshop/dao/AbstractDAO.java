package ru.tsystems.internetshop.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tsystems.internetshop.exception.DAOException;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * This abstract class contains common methods (CRUD and find (by PK, all)) for all entities
 * and implements DAO
 */
public abstract class AbstractDAO<T, PK> implements DAO<T, PK> {

    private final Class persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.persistentClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private final Logger fileLogger = Logger.getLogger("fileLogger");

    @Autowired
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * This method persists entity to database
     *
     * @param entity necessary entity object
     */
    @Override
    public void create(T entity) {
        try {
            getSession().persist(entity);
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method updates entity in database
     *
     * @param entity necessary entity object
     */
    @Override
    public void update(T entity) {
        try {
            getSession().update(entity);
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method deletes entity from database
     *
     * @param entity necessary entity object
     */
    @Override
    public void delete(T entity) {
        try {
            getSession().delete(entity);
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method gets entity from database
     *
     * @param key primary key for necessary entity
     * @return entity
     */
    @Override
    @SuppressWarnings("unchecked")
    public T findByKey(PK key) {
        try {
            return (T) getSession().get(persistentClass, (Serializable) key);
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method gets list of entities from database using HQL
     *
     * @return list of entities
     */
    @Override
    public List<T> findAll() {
        try {
            return getSession().createQuery("from " + persistentClass.getName()).list();
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }
}
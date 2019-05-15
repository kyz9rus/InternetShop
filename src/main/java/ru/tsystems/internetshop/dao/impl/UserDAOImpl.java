package ru.tsystems.internetshop.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.UserDAO;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.User;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class implements UserDAO and override its methods
 */
@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

    private final Logger fileLogger = Logger.getLogger("fileLogger");

    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * This method persist user entity to database
     *
     * @param user user
     */
    @Override
    public void create(User user) {
        try {
            sessionFactory.getCurrentSession().persist(user);
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method updates user entity in database
     *
     * @param user user
     */
    @Override
    public void update(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method deletes user entity from database
     *
     * @param user user
     */
    @Override
    public void delete(User user) {
        try {
            sessionFactory.getCurrentSession().delete(user);
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method gets user entity by primary key from database
     *
     * @param id user primary key
     */
    @Override
    public User findByKey(Long id) {
        try {
            return sessionFactory.getCurrentSession().get(User.class, id);
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method gets all users from database using HQL
     *
     * @return list of users
     */
    @Override
    public List<User> findAll() {
        try {
            return sessionFactory.getCurrentSession().createQuery("from user").list();
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method gets user entity by email from database using HQL
     *
     * @param email user email (unique)
     * @return user
     */
    @Override
    public UserDTO findByEmail(String email) {
        try {
            String queryString = "SELECT u FROM user u WHERE u.email= :email";

            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(queryString, User.class);
            query.setParameter("email", email);

            List<User> users = query.getResultList();

            if (!users.isEmpty()) {
                User user = users.get(0);
                UserDTO userDTO = null;
                try {
                    userDTO = user.clone();
                } catch (CloneNotSupportedException e) {
                    fileLogger.error(e);
                }
                return userDTO;
            } else
                return null;
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }

    /**
     * This method persist user entity or updates if it is already exist
     *
     * @param user user
     */
    @Override
    public void SaveOrUpdateUser(User user) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(user);
        } catch (Exception e) {
            fileLogger.error(e);
            throw new DAOException();
        }
    }
}

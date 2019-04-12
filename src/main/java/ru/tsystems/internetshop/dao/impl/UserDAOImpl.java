package ru.tsystems.internetshop.dao.impl;

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

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        try {
            sessionFactory.getCurrentSession().persist(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public void update(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public void delete(User user) {
        try {
            sessionFactory.getCurrentSession().delete(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public User findByKey(Long id) {
        try {
            return sessionFactory.getCurrentSession().get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return sessionFactory.getCurrentSession().createQuery("from user").list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

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
                    e.printStackTrace();
                }
                return userDTO;
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public void SaveOrUpdateUser(User user) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}

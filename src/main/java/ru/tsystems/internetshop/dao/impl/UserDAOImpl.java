package ru.tsystems.internetshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.UserDAO;
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
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User findByKey(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from user").list();
    }

    @Override
    public User findByEmail(String email) {
        String queryString = "SELECT u FROM user u WHERE u.email= :email";

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(queryString, User.class);
        query.setParameter("email", email);

        List<User> users = query.getResultList();

        if (!users.isEmpty())
            return users.get(0);
        else
            return null;
    }
}

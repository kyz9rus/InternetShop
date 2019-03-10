package ru.tsystems.internetshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.dao.UserDAO;
import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.ProductDto;
import ru.tsystems.internetshop.model.User;

import javax.persistence.TypedQuery;
import java.util.List;


@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByEmail(String email) {
        return sessionFactory.getCurrentSession().get(User.class, email);
    }
}

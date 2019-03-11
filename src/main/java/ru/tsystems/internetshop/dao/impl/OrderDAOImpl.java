package ru.tsystems.internetshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.Order;

import java.util.List;

@Repository
public class OrderDAOImpl extends AbstractDAO<Order, Long> implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order findById(Long id) {
        return findByKey(id);
//        return sessionFactory.getCurrentSession().find(Order.class, id);
    }

    @Override
    public void saveOrder(Order order) {
        create(order);
    }

    @Override
    public void updateOrder(Order order) {
        update(order);
    }

    @Override
    public List<Order> findAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("from ord").getResultList();
    }
}

package ru.tsystems.internetshop.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.model.OrderDto;

import java.util.List;


@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public OrderDto findById(int id) {
        return sessionFactory.getCurrentSession().find(OrderDto.class, id);
    }

    @Override
    public void saveOrder(OrderDto order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public void updateOrder(OrderDto order) {
        sessionFactory.getCurrentSession().update(order);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("from ord").getResultList();
    }
}

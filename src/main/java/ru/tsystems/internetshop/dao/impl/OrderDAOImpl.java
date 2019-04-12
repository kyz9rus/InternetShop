package ru.tsystems.internetshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Order;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class OrderDAOImpl extends AbstractDAO<Order, Long> implements OrderDAO {

    @Override
    public List<Order> findUnfinishedOrdersByClient(Client client) {
        try {
            String queryString = "SELECT o FROM ord o WHERE o.client.id = :id AND o.orderStatus != :order_status";

            TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(queryString, Order.class);
            query.setParameter("id", client.getId());
            query.setParameter("order_status", OrderStatus.DELIVERED);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public List<Order> findOrdersByClient(Client client) {
        try {
            String queryString = "SELECT o FROM ord o WHERE o.client.id = :id";

            TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(queryString, Order.class);
            query.setParameter("id", client.getId());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public List<Order> findOrdersByCategory(Category category) {
        try {
            String queryString = "select o from ord o left join o.orderProducts p where p.id in (select p2 from product p2 where p2.category = :category) and o.orderStatus != 'DELIVERED'";

            TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(queryString, Order.class);
            query.setParameter("category", category);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public List<Order> findPaidOrders() {
        try {
            String queryString = "SELECT o FROM ord o WHERE o.paymentStatus = :paymentStatus";

            TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(queryString, Order.class);
            query.setParameter("paymentStatus", PaymentStatus.PAID);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}

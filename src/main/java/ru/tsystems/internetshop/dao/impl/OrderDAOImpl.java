package ru.tsystems.internetshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Order;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class OrderDAOImpl extends AbstractDAO<Order, Long> implements OrderDAO {

    @Override
    public List<Order> findOrdersByClientAndWithDeliveredStatus(ClientDTO clientDTO) {
        String queryString = "SELECT o FROM ord o WHERE o.client.id = :id AND o.orderStatus != :order_status";

        TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(queryString, Order.class);
        query.setParameter("id", clientDTO.getId());
        query.setParameter("order_status", "delivered");

        return query.getResultList();
    }
}

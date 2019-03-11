package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.Order;
import ru.tsystems.internetshop.service.OrderService;

import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public void saveOrder(Order order) {
        orderDAO.saveOrder(order);
    }

    public List<Order> getOrders() {
        return orderDAO.findAllOrders();
    }

    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    public Order getOrder(Long id) {
        return orderDAO.findById(id);
    }
}

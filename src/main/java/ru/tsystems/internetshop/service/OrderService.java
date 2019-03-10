package ru.tsystems.internetshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.OrderDto;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public void saveOrder(OrderDto order) {
        orderDAO.saveOrder(order);
    }

    public List<OrderDto> getOrders() {
        return orderDAO.findAllOrders();
    }

    public void updateOrder(OrderDto order) {
        orderDAO.updateOrder(order);
    }

    public OrderDto getOrder(Long id) {
        return orderDAO.findById(id);
    }
}

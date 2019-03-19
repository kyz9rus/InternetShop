package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private Mapper mapper;

    public void saveOrder(OrderDTO orderDTO) {
        orderDAO.create(mapper.convertToEntity(orderDTO));
    }

    public List<OrderDTO> getOrders() {
        List<Order> orders = orderDAO.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for(Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));

        return orderDTOS;
    }

    @Override
    public List<OrderDTO> getOrdersByClientAndDeliveredStatus(ClientDTO clientDTO) {
        List<Order> orders = orderDAO.findOrdersByClientAndWithDeliveredStatus(clientDTO);
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));

        return orderDTOS;
    }

    public void updateOrder(OrderDTO orderDTO) {
        orderDAO.update(mapper.convertToEntity(orderDTO));
    }

    public OrderDTO getOrder(Long id) {
        Order order = orderDAO.findByKey(id);
        if (order != null)
            return mapper.convertToDto(order);
        else
            return null;
    }
}

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

    public void saveOrder(Order order) {
        orderDAO.create(order);
    }

    public List<Order> getOrders() {
        List<Order> orders = orderDAO.findAll();

//        try {
//            for (Order order : orders)
//                orderDTOs.add(order.clone());
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

//        OrderDTO orderDTO = convertToDto(orders.get(0));

//        System.out.println(orderDTO);

//        return orders.stream().map(this::convertToDto).collect(Collectors.toList());

        return orders;
    }

    @Override
    public List<OrderDTO> getOrdersByClientAndDeliveredStatus(ClientDTO clientDTO) {
        List<Order> orders = orderDAO.findOrdersByClientAndWithDeliveredStatus(clientDTO);
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));

        return orderDTOS;
    }

    public void updateOrder(Order order) {
        orderDAO.update(order);
    }

    public OrderDTO getOrder(Long id) {
//        OrderDTO orderDTO = convertToDto(orderDAO.findByKey(id));
        OrderDTO orderDTO = null;
//        try {
//            orderDTO = orderDAO.findByKey(id).clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

        return orderDTO;
    }
}

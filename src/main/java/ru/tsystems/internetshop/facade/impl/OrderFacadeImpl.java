package ru.tsystems.internetshop.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.facade.OrderFacade;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Mapper mapper;

    @Override
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderService.getOrders();

        return orders.stream().map(order -> mapper.convertToDto(order)).collect(Collectors.toList());
    }
}

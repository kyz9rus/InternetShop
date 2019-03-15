package ru.tsystems.internetshop.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.facade.OrderFacade;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderService.getOrders();

        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }

    private OrderDTO convertToDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    private OrderDTO toDto(Order order) {
        OrderDTO dto = new OrderDTO();
        BeanUtils.copyProperties(order, dto, "products");
        if (order.getProducts() != null) {
            dto.setProducts(dto.getProducts());
        }
        return dto;
    }
}

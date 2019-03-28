package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    private Map<String, DeliveryMethod> deliveryMethodMap = new HashMap<String, DeliveryMethod>() {{
        put("Post of Russia", DeliveryMethod.POST_OF_RUSSIA);
        put("Avon service centers", DeliveryMethod.AVON_SERVICE_CENTERS);
        put("Home delivery", DeliveryMethod.HOME_DELIVERY);
    }};

    private Map<String, PaymentMethod> paymentMethodMap = new HashMap<String, PaymentMethod>() {{
        put("By cash", PaymentMethod.CASH);
        put("By card", PaymentMethod.CARD);
    }};

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private Mapper mapper;

    public List<OrderDTO> getOrders() {
        List<Order> orders = orderDAO.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));

        return orderDTOS;
    }

    @Override
    public List<OrderDTO> getOrdersByClient(ClientDTO clientDTO) {
        List<Order> orders = orderDAO.getOrdersByClient(mapper.convertToEntity(clientDTO));
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));
        return orderDTOS;
    }

    @Override
    public List<OrderDTO> getUnfinishedOrdersByClient(ClientDTO clientDTO) {
        List<Order> orders = orderDAO.getUnfinishedOrdersByClient(mapper.convertToEntity(clientDTO));
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

    public DeliveryMethod getDeliveryMethod(String deliveryMethodString) {
        return deliveryMethodMap.get(deliveryMethodString);
    }

    public PaymentMethod getPaymentMethod(String paymentMethodString) {
        return paymentMethodMap.get(paymentMethodString);
    }
}

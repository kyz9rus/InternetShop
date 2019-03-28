package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.*;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.util.Mapper;

import java.time.LocalDate;
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

    private Map<String, PaymentStatus> paymentStatusMap = new HashMap<String, PaymentStatus>() {{
        put("waiting for payment", PaymentStatus.WAITING_FOR_PAYMENT);
        put("paid", PaymentStatus.PAID);
    }};

    private Map<String, OrderStatus> orderStatusMap = new HashMap<String, OrderStatus>() {{
        put("waiting for payment", OrderStatus.WAITING_FOR_PAYMENT);
        put("waiting for shipment", OrderStatus.WAITING_FOR_SHIPMENT);
        put("shipped", OrderStatus.SHIPPED);
        put("delivered", OrderStatus.DELIVERED);
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

    public PaymentStatus getPaymentStatus(String paymentStatusString) {
        return paymentStatusMap.get(paymentStatusString);
    }

    public OrderStatus getOrderStatus(String orderStatusString) {
        return orderStatusMap.get(orderStatusString);
    }

    @Override
    public List<OrderDTO> getOrdersByCategory(CategoryDTO categoryDTO) {
        List<Order> orders = orderDAO.getOrdersByCategory(mapper.convertToEntity(categoryDTO));
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));

        return orderDTOS;
    }

    @Override
    public RevenueInfo getRevenueInfo() {
        final long[] revenueForWeek = {0};
        final long[] revenueForMonth = {0};

        final LocalDate localDate = LocalDate.now();

        List<OrderDTO> orders = getPaidOrders();

        try {
            orders.stream().filter(orderDTO ->
                    orderDTO.getOrderDate().isBefore(localDate)
                            && orderDTO.getOrderDate().isAfter(localDate.minusWeeks(1))
            ).forEach(orderDTO -> revenueForWeek[0] += orderDTO.getPrice());

            orders.stream().filter(orderDTO ->
                    orderDTO.getOrderDate().isBefore(localDate)
                            && orderDTO.getOrderDate().isAfter(localDate.minusMonths(1))
            ).forEach(orderDTO -> revenueForMonth[0] += orderDTO.getPrice());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return new RevenueInfo(revenueForWeek[0], revenueForMonth[0]);
    }

    private List<OrderDTO> getPaidOrders() {
        List<Order> orders = orderDAO.getPaidOrders();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));

        return orderDTOS;
    }
}

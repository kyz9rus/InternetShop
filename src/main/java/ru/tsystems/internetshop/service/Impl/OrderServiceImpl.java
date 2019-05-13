package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.*;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.util.Mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is class, which implements methods from OrderService
 */
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
        put("waiting for shipment", OrderStatus.WAITING_FOR_SHIPMENT);
        put("shipped", OrderStatus.SHIPPED);
        put("delivered", OrderStatus.DELIVERED);
    }};

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private Mapper mapper;

    /**
     * This method gets all orders
     *
     * @return list of orders
     */
    @Override
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderDAO.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));

        return orderDTOS;
    }

    /**
     * This method gets orders by client
     *
     * @param clientDTO client
     * @return list of orders
     */
    @Override
    public List<OrderDTO> getOrdersByClient(ClientDTO clientDTO) {
        List<Order> orders = orderDAO.findOrdersByClient(mapper.convertToEntity(clientDTO));
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));
        return orderDTOS;
    }

    /**
     * This method gets only unfinished orders by client
     *
     * @param clientDTO client
     * @return list of orders
     */
    @Override
    public List<OrderDTO> getUnfinishedOrdersByClient(ClientDTO clientDTO) {
        List<Order> orders = orderDAO.findUnfinishedOrdersByClient(mapper.convertToEntity(clientDTO));
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));

        return orderDTOS;
    }

    /**
     * This method updates order
     *
     * @param orderDTO order
     */
    @Override
    public void updateOrder(OrderDTO orderDTO) {
        orderDAO.update(mapper.convertToEntity(orderDTO));
    }

    /**
     * This method gets order by id
     *
     * @param id order id
     * @return order or null (if it doesn't exist)
     */
    @Override
    public OrderDTO getOrder(Long id) {
        Order order = orderDAO.findByKey(id);
        if (order != null)
            return mapper.convertToDto(order);
        else
            return null;
    }

    /**
     * This method gets enum value from string value
     *
     * @param deliveryMethodString delivery method (String)
     * @return delivery method (enum)
     */
    public DeliveryMethod getDeliveryMethod(String deliveryMethodString) {
        return deliveryMethodMap.get(deliveryMethodString);
    }

    /**
     * This method gets enum value from string value
     *
     * @param paymentMethodString payment method (String)
     * @return payment method (enum)
     */
    public PaymentMethod getPaymentMethod(String paymentMethodString) {
        return paymentMethodMap.get(paymentMethodString);
    }

    /**
     * This method gets enum value from string value
     *
     * @param paymentStatusString payment status (String)
     * @return payment status (enum)
     */
    public PaymentStatus getPaymentStatus(String paymentStatusString) {
        return paymentStatusMap.get(paymentStatusString);
    }

    /**
     * This method gets enum value from string value
     *
     * @param orderStatusString order status (String)
     * @return order status (enum)
     */
    public OrderStatus getOrderStatus(String orderStatusString) {
        return orderStatusMap.get(orderStatusString);
    }

    /**
     * This method gets orders by category
     *
     * @param categoryDTO category
     * @return list of orders or empty list
     */
    @Override
    public List<OrderDTO> getOrdersByCategory(CategoryDTO categoryDTO) {
        List<Order> orders;
        try {
            orders = orderDAO.findOrdersByCategory(mapper.convertToEntity(categoryDTO));
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }

        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders)
            orderDTOS.add(mapper.convertToDto(order));

        return orderDTOS;
    }

    /**
     * This method gets revenue for a week and for a month
     *
     * @return revenue object
     */
    @Override
    public RevenueInfo getRevenueInfo() {
        final long[] revenueForWeek = {0};
        final long[] revenueForMonth = {0};

        final LocalDate localDate = LocalDate.now();

        List<OrderDTO> orders = getPaidOrders();

        try {
            orders.stream().filter(orderDTO ->
                    orderDTO.getOrderDate().isBefore(localDate)
                            && orderDTO.getOrderDate().isAfter(localDate.minusWeeks(1)) || orderDTO.getOrderDate().isEqual(localDate)
            ).forEach(orderDTO -> revenueForWeek[0] += orderDTO.getPrice());

            orders.stream().filter(orderDTO ->
                    orderDTO.getOrderDate().isBefore(localDate)
                            && orderDTO.getOrderDate().isAfter(localDate.minusMonths(1)) || orderDTO.getOrderDate().isEqual(localDate)
            ).forEach(orderDTO -> revenueForMonth[0] += orderDTO.getPrice());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println(revenueForMonth[0] + " " + revenueForWeek[0]);

        return new RevenueInfo(revenueForWeek[0], revenueForMonth[0]);
    }

    /**
     * This method saves order and gets order (with id)
     * @param orderDTO order
     * @return order (with id)
     */
    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        return mapper.convertToDto(orderDAO.createAndGet(mapper.convertToEntity(orderDTO)));
    }

    private List<OrderDTO> getPaidOrders() {
        List<Order> orders = orderDAO.findPaidOrders();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        System.out.println("PAID ORDERS: ");
        for (Order order : orders) {
            orderDTOS.add(mapper.convertToDto(order));
            System.out.println(order);
        }

        return orderDTOS;
    }
}

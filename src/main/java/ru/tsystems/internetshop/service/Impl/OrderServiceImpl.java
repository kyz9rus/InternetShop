package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.*;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    private Map<String, DeliveryMethod> deliveryMethodMap = new HashMap<String, DeliveryMethod>()
    {{
        put("Post of Russia", DeliveryMethod.postOfRussia);
        put("Avon service centers", DeliveryMethod.avonServiceCenters);
        put("Home delivery", DeliveryMethod.homeDelivery);
    }};

    private Map<String, PaymentMethod> paymentMethodMap = new HashMap<String, PaymentMethod>()
    {{
        put("By cash", PaymentMethod.cash);
        put("By card", PaymentMethod.card);
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

    @Override
    public void issueOrder(ClientDTO clientDTO, ClientAddressDTO clientAddressDTO, Basket basket, DeliveryMethod deliveryMethod, PaymentMethod paymentMethod) {
        Order order = new Order();
        order.setClient(mapper.convertToEntity(clientDTO));
        order.setClientAddress(mapper.convertToEntity(clientAddressDTO));
        order.setDeliveryMethod(deliveryMethod);
        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus(PaymentStatus.waitingForPayment);
        order.setOrderStatus(OrderStatus.waitingForPayment);

        List<ProductDTO> productDTOs = basket.getProducts();
        List<Product> products = new ArrayList<>();

        int price = 0;
        for (ProductDTO productDTO : productDTOs) {
            products.add(mapper.convertToEntity(productDTO));
            price += productDTO.getPrice();
        }

        order.setProducts(products);
        order.setPrice(price);

        orderDAO.create(order);
    }

    @Override
    public void repeatOrder(OrderDTO orderDTO) {
        orderDAO.create(mapper.convertToEntity(orderDTO));
    }

    public DeliveryMethod getDeliveryMethod(String deliveryMethodString) {
        return deliveryMethodMap.get(deliveryMethodString);
    }

    public PaymentMethod getPaymentMethod(String paymentMethodString) {
        return paymentMethodMap.get(paymentMethodString);
    }
}

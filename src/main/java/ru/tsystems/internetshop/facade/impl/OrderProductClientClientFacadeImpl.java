package ru.tsystems.internetshop.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.facade.OrderProductClientFacade;
import ru.tsystems.internetshop.model.*;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.util.Mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Component
public class OrderProductClientClientFacadeImpl implements OrderProductClientFacade {

    @Autowired
    private Mapper mapper;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Override
    public void issueOrder(ClientDTO clientDTO, ClientAddressDTO clientAddressDTO, Basket basket, DeliveryMethod deliveryMethod, PaymentMethod paymentMethod) {
        Order order = new Order();
        order.setClient(mapper.convertToEntity(clientDTO));
        order.setClientAddress(mapper.convertToEntity(clientAddressDTO));
        order.setDeliveryMethod(deliveryMethod);
        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT);
        order.setOrderStatus(OrderStatus.WAITING_FOR_PAYMENT);

        Map<ProductDTO, Integer> products = basket.getProducts();
        List<Product> productsToOrder = new ArrayList<>();
        int price = 0;

        for (ProductDTO productDTO : products.keySet()) {
            int numberProduct = products.get(productDTO);
            for (int i = 0; i < numberProduct; i++) {
                productDTO.setNumberOfSales(productDTO.getNumberOfSales() + 1);
                productsToOrder.add(mapper.convertToEntity(productDTO));
                price += productDTO.getPrice();

                productDAO.update(mapper.convertToEntity(productDTO));
            }
        }

        order.setProducts(productsToOrder);
        order.setPrice(price);

        order.setOrderDate(LocalDate.now());

        orderDAO.create(order);

        clientDAO.update(mapper.convertToEntity(clientDTO));
    }
}
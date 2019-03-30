package ru.tsystems.internetshop.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.facade.OrderProductClientFacade;
import ru.tsystems.internetshop.model.*;
import ru.tsystems.internetshop.model.DTO.*;
//import ru.tsystems.internetshop.service.OrderProductService;
import ru.tsystems.internetshop.model.entity.OrderProduct;
import ru.tsystems.internetshop.model.entity.Role;
import ru.tsystems.internetshop.model.entity.User;
import ru.tsystems.internetshop.service.OrderProductsService;
import ru.tsystems.internetshop.service.OrderService;
//import ru.tsystems.internetshop.service.OrderProductService;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.util.Mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Component
public class OrderProductClientFacadeImpl implements OrderProductClientFacade {

    @Autowired
    private Mapper mapper;

    @Autowired
    private OrderProductsService orderProductsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientDAO clientDAO;

    @Override
    public void issueOrder(ClientDTO clientDTO, ClientAddressDTO clientAddressDTO, Basket basket, DeliveryMethod deliveryMethod, PaymentMethod paymentMethod) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setClient(mapper.convertToEntity(clientDTO));
        orderDTO.setClientAddress(mapper.convertToEntity(clientAddressDTO));
        orderDTO.setDeliveryMethod(deliveryMethod);
        orderDTO.setPaymentMethod(paymentMethod);
        orderDTO.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT);
        orderDTO.setOrderStatus(OrderStatus.WAITING_FOR_PAYMENT);

        Map<ProductDTO, Integer> products = basket.getProducts();
        int price = 0;

        for (ProductDTO productDTO : products.keySet()) {
            int numberProduct = products.get(productDTO);
            for (int i = 0; i < numberProduct; i++) {
                productDTO.setNumberOfSales(productDTO.getNumberOfSales() + 1);
                price += productDTO.getPrice();
            }

            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setOrder(orderDTO);
            orderProductDTO.setProduct(productDTO);
            orderProductDTO.setAmount(products.get(productDTO));

            orderDTO.getOrderProducts().add(orderProductDTO);

            productService.updateProduct(productDTO);
        }

        orderDTO.setPrice(price);
        orderDTO.setOrderDate(LocalDate.now());
        clientDTO.setSummaryOrdersPrice((long) price);
        orderService.saveOrder(orderDTO);

        clientDAO.update(mapper.convertToEntity(clientDTO));
    }
}
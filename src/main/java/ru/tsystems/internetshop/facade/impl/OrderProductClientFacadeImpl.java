package ru.tsystems.internetshop.facade.impl;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.facade.OrderProductClientFacade;
import ru.tsystems.internetshop.model.*;
import ru.tsystems.internetshop.model.DTO.*;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.MailService;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.service.ProductService;

import java.time.LocalDate;
import java.util.Map;

/**
 * This class override methods from OrderProductClientFacade
 */
@Transactional
@Component
public class OrderProductClientFacadeImpl implements OrderProductClientFacade {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ClientService clientService;

    /**
     * This important method issue order, check payment method, update product and client statistic and send email to client
     *
     * @param clientDTO        client, who issued order
     * @param clientAddressDTO client address where the order will be sent
     * @param basket           basket where do products come from
     * @param deliveryMethod   delivery method
     * @param paymentMethod    payment method
     * @throws Exception
     */
    @Override
    public void issueOrder(ClientDTO clientDTO, ClientAddressDTO clientAddressDTO, Basket basket, DeliveryMethod deliveryMethod, PaymentMethod paymentMethod) throws Exception {
        OrderDTO orderDTO = new OrderDTO();

        if (basket.getCouponDTO() != null)
            clientDTO.getCoupons().add(basket.getCouponDTO());

        orderDTO.setClient(clientDTO);
        orderDTO.setClientAddress(clientAddressDTO);
        orderDTO.setDeliveryMethod(deliveryMethod);
        orderDTO.setPaymentMethod(paymentMethod);
        orderDTO.setOrderStatus(OrderStatus.WAITING_FOR_SHIPMENT);
        if (paymentMethod == PaymentMethod.CARD) {
            orderDTO.setPaymentStatus(PaymentStatus.PAID);
            clientDTO.setSummaryOrdersPrice(clientDTO.getSummaryOrdersPrice() + (long) basket.getSummaryPrice());
        } else
            orderDTO.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT);

        Map<ProductDTO, Integer> products = basket.getProducts();

        for (ProductDTO productDTO : products.keySet()) {
            int numberProduct = products.get(productDTO);
            productDTO.setNumberOfSales(productDTO.getNumberOfSales() + numberProduct);

            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setOrder(orderDTO);
            orderProductDTO.setProduct(productDTO);
            orderProductDTO.setAmount(products.get(productDTO));

            orderDTO.getOrderProducts().add(orderProductDTO);

            if (productDTO.getQuantityInStock() - numberProduct < 0)
                throw new Exception("Number of product is more than quantity in stock!");

            productDTO.setQuantityInStock(productDTO.getQuantityInStock() - numberProduct);

            productService.updateProduct(productDTO);
        }

        orderDTO.setPrice(basket.getSummaryPrice());
        orderDTO.setOrderDate(LocalDate.now());

        orderDTO = orderService.saveOrder(orderDTO);

        clientService.updateClient(clientDTO);

        try {
            mailService.sendNewOrderLetter(orderDTO);
        } catch (MailException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
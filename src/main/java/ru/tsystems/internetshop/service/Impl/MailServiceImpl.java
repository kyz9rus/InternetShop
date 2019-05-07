package ru.tsystems.internetshop.service.Impl;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DTO.OrderProductDTO;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.service.MailService;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSender emailSender;

    private final Logger fileLogger = Logger.getLogger("fileLogger");


    @Override
    public void sendNewCouponLetter(String email, CouponDTO couponDTO) throws SMTPSendFailedException, MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("danukrus@mail.ru");
        message.setTo(email);
        message.setSubject("NEW COUPON!");
        message.setText("Congratulations!\n\n" +
                        "" +
                        "You got a coupon:\n"
                        + couponDTO.getValue() + "\n\n" +
                        "Coupon description:\n"
                        + couponDTO.getDescription() + "\n\n" +
                        "Make orders with AVON!");

        fileLogger.info("Sending message: " + message + "...");
        emailSender.send(message);
    }

    @Override
    public void sendNewOrderLetter(OrderDTO orderDTO) throws SMTPSendFailedException, MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("danukrus@mail.ru");
        message.setTo(orderDTO.getClient().getEmail());
        message.setSubject("Your order (id: " + orderDTO.getId() + ")");

        ClientAddressDTO clientAddressDTO = orderDTO.getClientAddress();
        List<OrderProductDTO> productList = orderDTO.getOrderProducts();
        StringBuilder productsMessage = new StringBuilder();
        for (OrderProductDTO orderProductDTO : productList)
            productsMessage.append(orderProductDTO.getProduct().getName()).append(": amount - ").append(orderProductDTO.getAmount()).append(", price - ").append(orderProductDTO.getProduct().getPrice() * orderProductDTO.getAmount()).append(";\n");

        message.setText("Details of your order:\n" +
                "Order id: " + orderDTO.getId() + "\n" +
                "Order date: " + orderDTO.getOrderDate() + "\n" +
                "Delivery method: " + orderDTO.getDeliveryMethod().toString().replaceAll("_", " ") + "\n" +
                "Address: " + clientAddressDTO.getPostalCode() + ", " + clientAddressDTO.getCountry() + ", " + clientAddressDTO.getCity() + ", " + clientAddressDTO.getStreet() + ", " + clientAddressDTO.getHouse() + ", " + clientAddressDTO.getRoom() + "\n" +
                "Order summary price: " + orderDTO.getPrice() + "\n" +
                "Payment status: " + (orderDTO.getPaymentStatus() == PaymentStatus.PAID ? PaymentStatus.PAID : "payment on the spot") + "\n" +
                "Products:\n" +
                productsMessage + "\n\n" +
                "Make orders with AVON!");

        fileLogger.info("Sending message: " + message + "...");
        emailSender.send(message);
    }

    @Override
    public void sendChangeOrderStatusLetter(OrderDTO orderDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("danukrus@mail.ru");
        message.setTo(orderDTO.getClient().getEmail());
        message.setSubject("Order status has changed");

        message.setText("Order status for order with id " + orderDTO.getId() + " has changed!\n" +
                "New Order status: " + orderDTO.getOrderStatus() + "\n\n" +
                "Make orders with AVON!");

        fileLogger.info("Sending message: " + message + "...");
        emailSender.send(message);
    }

    @Override
    public void sendChangePaymentStatusLetter(OrderDTO orderDTO) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("danukrus@mail.ru");
        message.setTo(orderDTO.getClient().getEmail());
        message.setSubject("Payment status has changed");

        message.setText("Payment status for order with id " + orderDTO.getId() + " has changed!\n" +
                "New Payment status: " + orderDTO.getPaymentStatus() + "\n\n" +
                "Make orders with AVON!");

        fileLogger.info("Sending message: " + message + "...");
        emailSender.send(message);
    }
}

package ru.tsystems.internetshop.service.Impl;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DTO.OrderProductDTO;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.service.MailService;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendLetter(String email, CouponDTO couponDTO) throws SMTPSendFailedException {
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
        emailSender.send(message);
    }

    @Override
    public void sendLetter(String email, OrderDTO orderDTO) throws SMTPSendFailedException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("danukrus@mail.ru");
        message.setTo(email);
        message.setSubject("Your order");

        ClientAddressDTO clientAddressDTO = orderDTO.getClientAddress();
        List<OrderProductDTO> productList = orderDTO.getOrderProducts();
        StringBuilder productsMessage = new StringBuilder();
        for (OrderProductDTO orderProductDTO : productList)
            productsMessage.append(orderProductDTO.getProduct().getName()).append(": amount - ").append(orderProductDTO.getAmount()).append(", price - ").append(orderProductDTO.getProduct().getPrice() * orderProductDTO.getAmount()).append(";\n");

        message.setText("Details of your order:\n" +
                "Order date: " + orderDTO.getOrderDate() + "\n" +
                "Delivery method: " + orderDTO.getDeliveryMethod().toString().replaceAll("_", " ") + "\n" +
                "Address: " + clientAddressDTO.getPostalCode() + ", " + clientAddressDTO.getCountry() + ", " + clientAddressDTO.getCity() + ", " + clientAddressDTO.getStreet() + ", " + clientAddressDTO.getHouse() + ", " + clientAddressDTO.getRoom() + "\n" +
                "Order summary price: " + orderDTO.getPrice() + "\n" +
                "Payment status: " + (orderDTO.getPaymentStatus() == PaymentStatus.PAID ? PaymentStatus.PAID : "payment on the spot") + "\n" +
                "Products:\n" +
                productsMessage + "\n\n" +
                "Make orders with AVON!");
        emailSender.send(message);
    }
}

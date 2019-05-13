package ru.tsystems.internetshop.service;

import org.springframework.mail.MailException;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;

/**
 * This is interface, which declares methods for sending mails
 */
public interface MailService {
    void sendNewCouponLetter(String email, CouponDTO couponDTO) throws MailException;

    void sendNewOrderLetter(OrderDTO orderDTO) throws MailException;

    void sendChangeOrderStatusLetter(OrderDTO orderDTO) throws MailException;

    void sendChangePaymentStatusLetter(OrderDTO orderDTO) throws MailException;
}

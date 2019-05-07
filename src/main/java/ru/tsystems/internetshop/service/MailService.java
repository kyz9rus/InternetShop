package ru.tsystems.internetshop.service;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.springframework.mail.MailException;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;

public interface MailService {
    void sendNewCouponLetter(String email, CouponDTO couponDTO) throws MailException, SMTPSendFailedException;

    void sendNewOrderLetter(OrderDTO orderDTO) throws MailException, SMTPSendFailedException;

    void sendChangeOrderStatusLetter(OrderDTO orderDTO) throws MailException;

    void sendChangePaymentStatusLetter(OrderDTO orderDTO) throws MailException;
}

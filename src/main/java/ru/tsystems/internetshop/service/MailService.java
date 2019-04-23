package ru.tsystems.internetshop.service;

import com.sun.mail.smtp.SMTPSendFailedException;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;

public interface MailService {
    void sendLetter(String email, CouponDTO couponDTO) throws SMTPSendFailedException;
    void sendLetter(String email, OrderDTO orderDTO) throws SMTPSendFailedException;
}

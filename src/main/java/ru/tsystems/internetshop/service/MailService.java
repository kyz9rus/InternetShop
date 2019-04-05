package ru.tsystems.internetshop.service;

import com.sun.mail.smtp.SMTPSendFailedException;
import ru.tsystems.internetshop.model.DTO.CouponDTO;

public interface MailService {
    void sendLetter(String email, CouponDTO couponDTO) throws SMTPSendFailedException;
}

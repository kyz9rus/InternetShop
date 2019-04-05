package ru.tsystems.internetshop.service.Impl;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.service.MailService;

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
        message.setText("Congratulations!\n\nYou got a coupon:\n" + couponDTO.getValue() + "\n\nCoupon description:\n" + couponDTO.getDescription() + "\n\n Make orders with AVON!");
        emailSender.send(message);
    }
}

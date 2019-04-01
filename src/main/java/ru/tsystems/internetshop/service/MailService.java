package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.CouponDTO;

public interface MailService {
    void sendLetter(String email, CouponDTO couponDTO);
}

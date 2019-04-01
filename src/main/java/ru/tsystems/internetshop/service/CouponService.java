package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.CouponDTO;

public interface CouponService {
    CouponDTO getCouponByValue(String value);
}

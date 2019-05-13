package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.CouponDTO;

/**
 * This is interface, which declares methods for managing coupons
 */
public interface CouponService {
    CouponDTO getCouponByValue(String value);
}

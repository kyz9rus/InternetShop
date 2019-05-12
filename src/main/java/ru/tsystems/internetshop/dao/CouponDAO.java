package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Coupon;

/**
 * This interface declares methods for Coupon class
 */
public interface CouponDAO {
    Coupon findCouponByValue(String value);
}

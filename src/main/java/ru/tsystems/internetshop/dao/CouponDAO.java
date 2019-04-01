package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Coupon;

public interface CouponDAO {
    Coupon findCouponByValue(String value);
}

package ru.tsystems.internetshop.service;

import org.springframework.ui.Model;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.util.ResponseInfo;

/**
 * This is interface, which declares methods for managing coupons
 */
public interface CouponService {
    CouponDTO getCouponByValue(String value);

    ResponseInfo sendCoupon(ClientDTO clientDTO, String couponName);

    ResponseInfo checkCoupon(String couponValue, ClientDTO clientDTO, Model model);
}

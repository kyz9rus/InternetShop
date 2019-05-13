package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.CouponDAO;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.service.BasketService;
import ru.tsystems.internetshop.service.CouponService;
import ru.tsystems.internetshop.util.Mapper;

/**
 * This is class, which implements methods from CouponService
 */
@Transactional
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDAO couponDAO;

    @Autowired
    private Mapper mapper;

    /**
     * This method gets coupon by value
     * @param value coupon value
     * @return coupon or null (if it doesn't exist)
     */
    @Override
    public CouponDTO getCouponByValue(String value) {
        try {
            return mapper.convertToDto(couponDAO.findCouponByValue(value));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

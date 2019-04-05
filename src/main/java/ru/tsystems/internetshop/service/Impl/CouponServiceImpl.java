package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.CouponDAO;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.service.CouponService;
import ru.tsystems.internetshop.util.Mapper;

@Transactional
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDAO couponDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public CouponDTO getCouponByValue(String name) {
        try {
            return mapper.convertToDto(couponDAO.findCouponByValue(name));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

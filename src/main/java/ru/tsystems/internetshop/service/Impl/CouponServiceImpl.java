package ru.tsystems.internetshop.service.Impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.tsystems.internetshop.dao.CouponDAO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.service.CouponService;
import ru.tsystems.internetshop.service.MailService;
import ru.tsystems.internetshop.util.Mapper;
import ru.tsystems.internetshop.util.ResponseInfo;

/**
 * This is class, which implements methods from CouponService
 */
@Transactional
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDAO couponDAO;

    @Autowired
    private MailService mailService;

    @Autowired
    private Mapper mapper;

    private final Logger fileLogger = Logger.getLogger("fileLogger");

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

    /**
     * This method checks that coupon for the client didn't use and send it to client email
     * @param clientDTO client
     * @param couponValue coupon value
     * @return response info (message and status)
     */
    @Override
    public ResponseInfo sendCoupon(ClientDTO clientDTO, String couponValue) {
        String email = clientDTO.getEmail();
        ResponseInfo responseInfo;

        CouponDTO couponDTO = getCouponByValue(couponValue);

        if (couponDTO != null) {
            if (clientDTO.getCoupons().contains(couponDTO))
                responseInfo = new ResponseInfo("You have already used this coupon.", 404);
            else {
                try {
                    mailService.sendNewCouponLetter(email, couponDTO);
                    responseInfo = new ResponseInfo("Сoupon successfully sent.\nCheck your email.", 200);
                } catch (MailException e) {
                    fileLogger.error(e.getMessage());
                    responseInfo = new ResponseInfo("Incorrect email. Change it in your profile!", 404);
                }
            }
        } else {
            responseInfo = new ResponseInfo("Coupon is not available.", 404);
        }

        return responseInfo;
    }

    /**
     * This method checks that coupon entered correctly and don't use by the client
     * @param couponValue coupon value
     * @param clientDTO client
     * @return model
     */
    @Override
    public ResponseInfo checkCoupon(String couponValue, ClientDTO clientDTO, Model model) {
        ResponseInfo responseInfo;

        CouponDTO couponDTO = getCouponByValue(couponValue);
        if (couponDTO == null)
            responseInfo = new ResponseInfo("Incorrect coupon", 404);
        else if (clientDTO.getCoupons().contains(couponDTO))
            responseInfo = new ResponseInfo("You have already use this coupon", 404);
        else
            responseInfo = new ResponseInfo("Correct coupon", 200);

        return responseInfo;
    }
}

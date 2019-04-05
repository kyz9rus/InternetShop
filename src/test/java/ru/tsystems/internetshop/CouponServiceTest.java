package ru.tsystems.internetshop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.dao.CouponDAO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Coupon;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.CouponService;
import ru.tsystems.internetshop.service.Impl.ClientServiceImpl;
import ru.tsystems.internetshop.service.Impl.CouponServiceImpl;
import ru.tsystems.internetshop.util.Mapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)


@ContextConfiguration

public class CouponServiceTest {

    @Configuration
    static class ClientServiceTestContextConfiguration {
        @Bean
        public CouponService couponService() {
            return new CouponServiceImpl();
        }

        @Bean
        public CouponDAO couponDAO() {
            return Mockito.mock(CouponDAO.class);
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public Mapper mapper() {
            return new Mapper();
        }
    }

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponDAO couponDAO;

    @Before
    public void setup() {
        Coupon coupon = new Coupon();
        coupon.setValue("COOL_ORDER");

        Mockito.when(couponDAO.findCouponByValue("COOL_ORDER")).thenReturn(coupon);
    }

    @Test
    public void testGetCouponByValue() {
        CouponDTO couponDTO = couponService.getCouponByValue("COOL_ORDER");

        assertEquals("COOL_ORDER", couponDTO.getValue());
    }

    @Test
    public void testGetCouponByValueFail() {
        CouponDTO couponDTO = couponService.getCouponByValue("COOL_ORDER2");

        assertNull(couponDTO);
    }

    @After
    public void verify() {
        Mockito.reset(couponDAO);
    }
}


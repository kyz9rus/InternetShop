package ru.tsystems.internetshop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.service.BasketService;
import ru.tsystems.internetshop.service.Impl.BasketServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BasketServiceTest {

    @Configuration
    static class ClientServiceTestContextConfiguration {
        @Bean
        public BasketService basketService() {
            return Mockito.mock(BasketService.class);
        }
    }

    @Autowired
    private BasketService basketService;

    @Before
    public void setup() {
        Basket basket = new Basket();

        Map<ProductDTO, Integer> productsMap = new HashMap<>();

        productsMap.put(new ProductDTO(1L, 250), 1);
        productsMap.put(new ProductDTO(2L, 125), 2);
        productsMap.put(new ProductDTO(3L, 500), 1);

        basket.setProducts(productsMap);

        Mockito.when(basketService.calcPrice(basket)).thenReturn(1000);
    }

    @Test
    public void testCalcPrice() {
        Basket basket = new Basket();

        Map<ProductDTO, Integer> productsMap = new HashMap<>();

        productsMap.put(new ProductDTO(1L, 250), 1);
        productsMap.put(new ProductDTO(2L, 125), 2);
        productsMap.put(new ProductDTO(3L, 500), 1);

        basket.setProducts(productsMap);

        int price = basketService.calcPrice(basket);

        assertEquals(1000, price);
    }
}


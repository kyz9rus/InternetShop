package ru.tsystems.internetshop.service.Impl;

import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.service.BasketService;

import java.util.Map;

@Service
public class BasketServiceImpl implements BasketService {

    @Override
    public int calcPrice(Basket basket) {
        int price = 0;

        CouponDTO couponDTO = basket.getCouponDTO();
        if (couponDTO.getValue().equals("HAPPY_ORDER")) {
            System.out.println("WITHOUT: " + calcPriceWithoutDiscount(basket) + " | HAPPY: " + calcPriceWithoutDiscount(basket) * 0.7);
            price = (int) Math.round(calcPriceWithoutDiscount(basket) * 0.7);
            System.out.println("PRICE: " + price);
        }
        else if (couponDTO.getValue().equals("HAPPY_FRAGRANCES")) {
            Map<ProductDTO, Integer> products = basket.getProducts();

            for (ProductDTO productDTO : products.keySet()) {
                int numberProduct = products.get(productDTO);

                for (int i = 0; i < numberProduct; i++) {
                    productDTO.setNumberOfSales(productDTO.getNumberOfSales() + 1);
                    if (productDTO.getCategory().getName().equals("fragrances"))
                        price += productDTO.getPrice()*0.85;
                    else
                        price += productDTO.getPrice();
                }
            }
        } else
            price = calcPriceWithoutDiscount(basket);


        return price;
    }

    @Override
    public int calcPriceWithoutDiscount(Basket basket) {
        Map<ProductDTO, Integer> products = basket.getProducts();
        int price = 0;

        for (ProductDTO productDTO : products.keySet()) {
            int numberProduct = products.get(productDTO);

            for (int i = 0; i < numberProduct; i++) {
                productDTO.setNumberOfSales(productDTO.getNumberOfSales() + 1);
                price += productDTO.getPrice();
            }
        }

        return price;
    }

    @Override
    public void resetDiscount(Basket basket) {
        basket.setCouponDTO(null);
        basket.setSummaryPrice(calcPriceWithoutDiscount(basket));
    }
}

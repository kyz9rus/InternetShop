package ru.tsystems.internetshop.service.Impl;

import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.service.BasketService;

import java.util.Map;

/**
 * This is class, which implements methods from BasketService
 */
@Service
public class BasketServiceImpl implements BasketService {

    /**
     * This method calculates price for products in basket and using coupon (if it has)
     *
     * @param basket current basket with products and coupon
     * @return summary price with dicount
     */
    @Override
    public int calcPrice(Basket basket) {
        int price = 0;

        CouponDTO couponDTO = basket.getCouponDTO();
        if (couponDTO.getValue().equals("HAPPY_ORDER"))
            price = (int) Math.round(calcPriceWithoutDiscount(basket) * 0.7);
        else if (couponDTO.getValue().equals("HAPPY_FRAGRANCES")) {
            Map<ProductDTO, Integer> products = basket.getProducts();

            for (ProductDTO productDTO : products.keySet()) {
                int numberProduct = products.get(productDTO);

                for (int i = 0; i < numberProduct; i++) {
                    productDTO.setNumberOfSales(productDTO.getNumberOfSales() + 1);
                    if (productDTO.getCategory().getName().equals("fragrances"))
                        price += productDTO.getPrice() * 0.85;
                    else
                        price += productDTO.getPrice();
                }
            }
        } else
            price = calcPriceWithoutDiscount(basket);

        return price;
    }

    /**
     * This method calculates price for products in basket and doesn't use coupon
     *
     * @param basket basket with products without coupon
     * @return summary price
     */
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

    /**
     * This method resets discount for basket and remove coupon (if it has)
     *
     * @param basket basket
     */
    @Override
    public void resetDiscount(Basket basket) {
        basket.setCouponDTO(null);
        basket.setSummaryPrice(calcPriceWithoutDiscount(basket));
    }
}

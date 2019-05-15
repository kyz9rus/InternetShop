package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.service.BasketService;
import ru.tsystems.internetshop.service.CouponService;
import ru.tsystems.internetshop.service.ProductService;

import java.util.Map;

/**
 * This is class, which implements methods from BasketService
 */
@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private ProductService productService;

    @Autowired
    private CouponService couponService;

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

    /**
     * This method puts product to the basket
     *
     * @param basket current basket
     * @param productId product id
     * @return product
     */
    @Override
    public ProductDTO putProduct(Basket basket, Long productId) {
        ProductDTO productDTO = productService.getProduct(productId);
        basket.addProduct(productDTO);

        if (basket.getCouponDTO() != null) {
            basket.setChangedAfterCoupon(true);

            basket.setSummaryPrice(calcPriceWithoutDiscount(basket));
        }
        return productDTO;
    }

    /**
     * This method removes 1 position for the product
     *
     * @param basket current basket
     * @param productId product id
     * @return product
     */
    @Override
    public ProductDTO decreaseProduct(Basket basket, Long productId) {
        ProductDTO productDTO = productService.getProduct(productId);
        basket.decreaseProduct(productDTO);

        return productDTO;
    }

    /**
     * This method removes all positions of the product from basket
     *
     * @param basket current basket
     * @param productId product id
     * @return product
     */
    @Override
    public ProductDTO removeProduct(Basket basket, Long productId) {
        ProductDTO productDTO = productService.getProduct(productId);
        basket.removeProduct(productDTO);

        return productDTO;
    }

    /**
     * This method apllies coupon and calculates new price in basket
     * @param couponValue coupon string value
     * @param basket basket
     */
    @Override
    public void applyCoupon(String couponValue, Basket basket) {
        CouponDTO couponDTO = couponService.getCouponByValue(couponValue);

        if (couponDTO != null && (basket.getCouponDTO() == null || basket.isChangedAfterCoupon())) {
            basket.setCouponDTO(couponDTO);
            basket.setSummaryPrice(calcPrice(basket));

            basket.setChangedAfterCoupon(false);
        }
    }
}

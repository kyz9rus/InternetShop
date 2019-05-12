package ru.tsystems.internetshop.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * This class describes basket and models basket in a real shop
 */
@Data
@Component
public class Basket {
    private Map<ProductDTO, Integer> products;
    private int numberOfProducts;
    private int summaryPrice;
    private CouponDTO couponDTO;
    private boolean isChangedAfterCoupon;

    public Basket() {
        products = new HashMap<>();
    }

    public Basket(Map<ProductDTO, Integer> products, int numberOfProducts, int summaryPrice) {
        this.products = products;
        this.numberOfProducts = numberOfProducts;
        this.summaryPrice = summaryPrice;
    }

    public Map<ProductDTO, Integer> getProducts() {
        return products;
    }

    /**
     * This method adds product to basket
     *
     * @param productDTO product
     */
    public void addProduct(ProductDTO productDTO) {
        if (products.containsKey(productDTO))
            products.put(productDTO, products.get(productDTO) + 1);
        else
            products.put(productDTO, 1);

        numberOfProducts++;
        summaryPrice += productDTO.getPrice();
    }

    /**
     * This method decrease the product in basket
     *
     * @param productDTO product
     */
    public void decreaseProduct(ProductDTO productDTO) {
        int numberOfProduct = products.get(productDTO);
        if (numberOfProduct > 1)
            products.put(productDTO, --numberOfProduct);
        else
            products.remove(productDTO);
        numberOfProducts--;

        summaryPrice -= productDTO.getPrice();
    }

    /**
     * This method deletes all positions of the product
     *
     * @param productDTO product which one we want to remove from basket
     */
    public void removeProduct(ProductDTO productDTO) {
        int numberOfProducts = products.get(productDTO);

        this.numberOfProducts -= numberOfProducts;
        this.summaryPrice -= numberOfProducts * productDTO.getPrice();

        products.remove(productDTO);
    }
}
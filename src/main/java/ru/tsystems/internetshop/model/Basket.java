package ru.tsystems.internetshop.model;

import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * This class describes basket and models basket in a real shop
 */
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

    public int getNumberOfProducts() {
        return this.numberOfProducts;
    }

    public int getSummaryPrice() {
        return this.summaryPrice;
    }

    public CouponDTO getCouponDTO() {
        return this.couponDTO;
    }

    public boolean isChangedAfterCoupon() {
        return this.isChangedAfterCoupon;
    }

    public void setProducts(Map<ProductDTO, Integer> products) {
        this.products = products;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public void setSummaryPrice(int summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public void setCouponDTO(CouponDTO couponDTO) {
        this.couponDTO = couponDTO;
    }

    public void setChangedAfterCoupon(boolean isChangedAfterCoupon) {
        this.isChangedAfterCoupon = isChangedAfterCoupon;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Basket)) return false;
        final Basket other = (Basket) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$products = this.getProducts();
        final Object other$products = other.getProducts();
        if (this$products == null ? other$products != null : !this$products.equals(other$products)) return false;
        if (this.getNumberOfProducts() != other.getNumberOfProducts()) return false;
        if (this.getSummaryPrice() != other.getSummaryPrice()) return false;
        final Object this$couponDTO = this.getCouponDTO();
        final Object other$couponDTO = other.getCouponDTO();
        if (this$couponDTO == null ? other$couponDTO != null : !this$couponDTO.equals(other$couponDTO)) return false;
        if (this.isChangedAfterCoupon() != other.isChangedAfterCoupon()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Basket;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $products = this.getProducts();
        result = result * PRIME + ($products == null ? 43 : $products.hashCode());
        result = result * PRIME + this.getNumberOfProducts();
        result = result * PRIME + this.getSummaryPrice();
        final Object $couponDTO = this.getCouponDTO();
        result = result * PRIME + ($couponDTO == null ? 43 : $couponDTO.hashCode());
        result = result * PRIME + (this.isChangedAfterCoupon() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "Basket(products=" + this.getProducts() + ", numberOfProducts=" + this.getNumberOfProducts() + ", summaryPrice=" + this.getSummaryPrice() + ", couponDTO=" + this.getCouponDTO() + ", isChangedAfterCoupon=" + this.isChangedAfterCoupon() + ")";
    }
}
package ru.tsystems.internetshop.model;

import org.springframework.stereotype.Component;

/**
 * This class contains information about basket for current client or guest
 */
@Component
public class BasketInfo {
    private Basket basket;
    private Integer numberOfProduct;

    public BasketInfo() {
        basket = new Basket();
    }

    public BasketInfo(Basket basket, Integer numberOfProduct) {
        this.basket = basket;
        this.numberOfProduct = numberOfProduct;
    }

    public Basket getBasket() {
        return this.basket;
    }

    public Integer getNumberOfProduct() {
        return this.numberOfProduct;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setNumberOfProduct(Integer numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BasketInfo)) return false;
        final BasketInfo other = (BasketInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$basket = this.getBasket();
        final Object other$basket = other.getBasket();
        if (this$basket == null ? other$basket != null : !this$basket.equals(other$basket)) return false;
        final Object this$numberOfProduct = this.getNumberOfProduct();
        final Object other$numberOfProduct = other.getNumberOfProduct();
        if (this$numberOfProduct == null ? other$numberOfProduct != null : !this$numberOfProduct.equals(other$numberOfProduct))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BasketInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $basket = this.getBasket();
        result = result * PRIME + ($basket == null ? 43 : $basket.hashCode());
        final Object $numberOfProduct = this.getNumberOfProduct();
        result = result * PRIME + ($numberOfProduct == null ? 43 : $numberOfProduct.hashCode());
        return result;
    }

    public String toString() {
        return "BasketInfo(basket=" + this.getBasket() + ", numberOfProduct=" + this.getNumberOfProduct() + ")";
    }
}
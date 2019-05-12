package ru.tsystems.internetshop.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * This class is DTO for OrderProduct entity
 */
public class OrderProductDTO {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private OrderDTO order;
    private ProductDTO product;
    private int amount;

    public OrderProductDTO() {
    }

    public Long getId() {
        return this.id;
    }

    public OrderDTO getOrder() {
        return this.order;
    }

    public ProductDTO getProduct() {
        return this.product;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductDTO orderProductDTO = (OrderProductDTO) o;
        return Objects.equals(id, orderProductDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderProductDTO(id=" + this.getId() + ", order=" + this.getOrder() + ", product=" + this.getProduct() + ", amount=" + this.getAmount() + ")";
    }
}

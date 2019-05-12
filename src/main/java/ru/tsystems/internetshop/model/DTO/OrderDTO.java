package ru.tsystems.internetshop.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is DTO for order entity
 */
public class OrderDTO {
    private Long id;

    @JsonIgnore
    @NotNull
    private ClientDTO client;

    @JsonIgnore
    @NotNull
    private ClientAddressDTO clientAddress;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private DeliveryMethod deliveryMethod;
    private List<OrderProductDTO> orderProducts = new ArrayList<>();

    @NotNull
    private PaymentStatus paymentStatus;

    @NotNull
    private OrderStatus orderStatus;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private int price;

    public OrderDTO() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientAddress=" + clientAddress +
                ", paymentMethod=" + paymentMethod +
                ", deliveryMethod=" + deliveryMethod +
                ", paymentStatus=" + paymentStatus +
                ", orderStatus=" + orderStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return this.id;
    }

    public @NotNull ClientDTO getClient() {
        return this.client;
    }

    public @NotNull ClientAddressDTO getClientAddress() {
        return this.clientAddress;
    }

    public @NotNull PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public @NotNull DeliveryMethod getDeliveryMethod() {
        return this.deliveryMethod;
    }

    public List<OrderProductDTO> getOrderProducts() {
        return this.orderProducts;
    }

    public @NotNull PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public @NotNull OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public @NotNull LocalDate getOrderDate() {
        return this.orderDate;
    }

    public @NotNull int getPrice() {
        return this.price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(@NotNull ClientDTO client) {
        this.client = client;
    }

    public void setClientAddress(@NotNull ClientAddressDTO clientAddress) {
        this.clientAddress = clientAddress;
    }

    public void setPaymentMethod(@NotNull PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setDeliveryMethod(@NotNull DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void setPaymentStatus(@NotNull PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setOrderStatus(@NotNull OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderDate(@NotNull LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setPrice(@NotNull int price) {
        this.price = price;
    }
}

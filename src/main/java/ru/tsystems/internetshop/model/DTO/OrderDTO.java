package ru.tsystems.internetshop.model.DTO;

import lombok.Data;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.model.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class OrderDTO {
    private Long id;

    @NotNull
    private Client client;

    @NotNull
    private ClientAddress clientAddress;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private DeliveryMethod deliveryMethod;
    private List<Product> products = new ArrayList<>();

    @NotNull
    private PaymentStatus paymentStatus;

    @NotNull
    private OrderStatus orderStatus;

    @NotNull
    private int price;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", paymentMethod=" + paymentMethod +
                ", deliveryMethod=" + deliveryMethod +
                ", paymentStatus=" + paymentStatus +
                ", orderStatus=" + orderStatus +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return price == orderDTO.price &&
                Objects.equals(id, orderDTO.id) &&
                paymentMethod == orderDTO.paymentMethod &&
                deliveryMethod == orderDTO.deliveryMethod &&
                Objects.equals(products, orderDTO.products) &&
                paymentStatus == orderDTO.paymentStatus &&
                orderStatus == orderDTO.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, deliveryMethod, products, paymentStatus, orderStatus, price);
    }
}

package ru.tsystems.internetshop.model.DTO;

import lombok.Data;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.model.entity.Product;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class OrderDTO {
    private Long id;
    private Client client;
    private ClientAddress clientAddress;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private Set<Product> products = new HashSet<>();
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
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

package ru.tsystems.internetshop.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is DTO for order entity
 */
@Data
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
        return Objects.equals(id, orderDTO.id) &&
                paymentMethod == orderDTO.paymentMethod &&
                deliveryMethod == orderDTO.deliveryMethod &&
                paymentStatus == orderDTO.paymentStatus &&
                orderStatus == orderDTO.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, deliveryMethod, paymentStatus, orderStatus);
    }
}

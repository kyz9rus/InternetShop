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

//    @Override
//    public Order clone() throws CloneNotSupportedException {
//        Order order = new Order();
//        order.setId(id);
//        order.setOrderStatus(orderStatus);
//        order.setDeliveryMethod(deliveryMethod);
//        order.setPaymentMethod(paymentMethod);
//        order.setPaymentStatus(paymentStatus);
//
//        {
//            Client client = new Client();
//            client.setId(this.client.getId());
//            client.setPassword(this.client.getPassword());
//            client.setBirthday(this.client.getBirthday());
//            client.setEmail(this.client.getEmail());
//            client.setFirstName(this.client.getFirstName());
//            client.setLastName(this.client.getLastName());
//
//            Set<ClientAddress> clientAddresses = new HashSet<>();
//            for (ClientAddressDTO clientAddressDTO : this.client.getAddresses())
//                clientAddresses.add(clientAddressDTO.clone());
//            client.setAddresses(clientAddresses);
//
//            Set<Order> orders = new HashSet<>();
//            for (OrderDTO orderDTO : this.client.getOrders())
//                orders.add(orderDTO.clone());
//            client.setOrders(orders);
//
//            order.setClient(client);
//        }
//
//        {
//            ClientAddress clientAddress = new ClientAddress();
//            clientAddress.setCity(this.clientAddress.getCity());
//            clientAddress.setCountry(this.clientAddress.getCountry());
//            clientAddress.setHouse(this.clientAddress.getHouse());
//            clientAddress.setId(this.clientAddress.getId());
//            clientAddress.setRoom(this.clientAddress.getRoom());
//            clientAddress.setPostalCode(this.clientAddress.getPostalCode());
//            clientAddress.setStreet(this.clientAddress.getStreet());
//            clientAddress.setClient(this.clientAddress.getClient().clone());
//
//            Set<Order> orders = new HashSet<>();
//            for (OrderDTO orderDTO : this.clientAddress.getOrders())
//                orders.add(orderDTO.clone());
//            clientAddress.setOrders(orders);
//
//            order.setClientAddress(clientAddress);
//        }
//
//        return order;
//    }
}

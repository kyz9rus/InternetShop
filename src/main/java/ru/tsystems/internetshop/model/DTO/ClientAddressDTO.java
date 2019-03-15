package ru.tsystems.internetshop.model.DTO;

import lombok.Data;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.model.entity.Order;

import java.util.HashSet;
import java.util.Set;

@Data
public class ClientAddressDTO {
    private Long id;
    private ClientDTO client;
    private String country;
    private String city;
    private int postalCode;
    private String street;
    private String house;
    private int room;
    private Set<OrderDTO> orders = new HashSet<>();

//    @Override
//    protected ClientAddress clone() throws CloneNotSupportedException {
//        ClientAddress clientAddress = new ClientAddress();
//        clientAddress.setId(id);
//        clientAddress.setCountry(country);
//        clientAddress.setCity(city);
//        clientAddress.setPostalCode(postalCode);
//        clientAddress.setStreet(street);
//        clientAddress.setHouse(house);
//        clientAddress.setRoom(room);
//
//        clientAddress.setClient(client.clone());
//
//        Set<Order> orders = new HashSet<>();
//        for (OrderDTO orderDTO : this.orders)
//            orders.add(orderDTO.clone());
//
//        clientAddress.setOrders(orders);
//
//        return clientAddress;
//    }
}

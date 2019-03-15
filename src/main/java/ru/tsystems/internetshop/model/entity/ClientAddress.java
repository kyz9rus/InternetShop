package ru.tsystems.internetshop.model.entity;

import lombok.Data;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "clientAddress")
@Table(name = "clientaddress")
public class ClientAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientaddress_seq")
    @SequenceGenerator(name = "clientaddress_seq", sequenceName = "SEQ_CLIENTADDRESS", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    @Column(name = "postal_code")
    private int postalCode;

    @NotNull
    private String street;

    @NotNull
    private String house;

    @NotNull
    private int room;

    @OneToMany(mappedBy = "clientAddress", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

//    @Override
//    public ClientAddressDTO clone() throws CloneNotSupportedException {
//        ClientAddressDTO clientAddressDTO = new ClientAddressDTO();
//        clientAddressDTO.setCity(city);
//        clientAddressDTO.setCountry(country);
//        clientAddressDTO.setHouse(house);
//        clientAddressDTO.setId(id);
//        clientAddressDTO.setPostalCode(postalCode);
//        clientAddressDTO.setRoom(room);
//        clientAddressDTO.setStreet(street);
//
//        Set<OrderDTO> orderDTOs = new HashSet<>();
//        for (Order order : orders)
//            orderDTOs.add(order.clone());
//
//        clientAddressDTO.setOrders(orderDTOs);
//
//        return clientAddressDTO;
//    }
}

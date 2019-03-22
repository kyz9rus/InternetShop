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
}

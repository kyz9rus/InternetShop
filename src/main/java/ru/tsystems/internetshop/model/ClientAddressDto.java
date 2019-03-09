package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "clientAddress")
@Table(name = "clientAddress")
public class ClientAddressDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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

//    @OneToMany(mappedBy = "clientAddress")
//    private Set<OrderDto> orders = new HashSet<>();
}

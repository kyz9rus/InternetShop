package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "clientAddress")
@Table(name = "clientAddress")
public class ClientAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientAddress_seq")
    @SequenceGenerator(name = "clientAddress_seq", sequenceName = "SEQ_CLIENTADDRESS", allocationSize = 1)
    private Long id;

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

    @OneToMany(mappedBy = "clientAddress")
    private Set<Order> orders = new HashSet<>();
}

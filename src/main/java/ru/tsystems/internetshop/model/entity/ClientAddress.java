package ru.tsystems.internetshop.model.entity;

import lombok.Data;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity(name = "clientAddress")
@Table(name = "clientaddress")
public class ClientAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientaddress_seq")
    @SequenceGenerator(name = "clientaddress_seq", sequenceName = "SEQ_CLIENTADDRESS", allocationSize = 1)
    private Long id;

    @ManyToOne
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

    @OneToMany(mappedBy = "clientAddress", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @Override
    public String toString() {
        return "ClientAddress{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", room=" + room +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAddress that = (ClientAddress) o;
        return postalCode == that.postalCode &&
                room == that.room &&
                Objects.equals(id, that.id) &&
                Objects.equals(country, that.country) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(house, that.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, postalCode, street, house, room);
    }
}

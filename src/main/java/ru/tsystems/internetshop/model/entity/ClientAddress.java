package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity(name = "clientAddress")
@Table(name = "clientaddress")
public class ClientAddress {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String country;

    private String city;

    @Column(name = "postal_code")
    private int postalCode;

    private String street;

    private String house;

    private int room;

    @OneToMany(mappedBy = "clientAddress", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public ClientAddress() {
    }

    public ClientAddress(Client client, String country, String city, int postalCode, String street, String house, int room, List<Order> orders) {
        this.client = client;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.house = house;
        this.room = room;
        this.orders = orders;
    }

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

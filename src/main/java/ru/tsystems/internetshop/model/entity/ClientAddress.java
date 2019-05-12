package ru.tsystems.internetshop.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is client address entity
 */
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

    public ClientAddress(long id) {
        this.id = id;
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return this.id;
    }

    public Client getClient() {
        return this.client;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }

    public int getPostalCode() {
        return this.postalCode;
    }

    public String getStreet() {
        return this.street;
    }

    public String getHouse() {
        return this.house;
    }

    public int getRoom() {
        return this.room;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

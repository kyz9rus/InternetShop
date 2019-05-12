package ru.tsystems.internetshop.model.DTO;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is DTO for ClientAddress entity
 */
public class ClientAddressDTO {

    private Long id;

    @NotNull
    private ClientDTO client;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private int postalCode;

    @NotNull
    private String street;

    @NotNull
    private String house;

    @NotNull
    private int room;


    private List<OrderDTO> orders = new ArrayList<>();

    public ClientAddressDTO() {
    }

    @Override
    public String toString() {
        return "ClientAddressDTO{" +
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
        ClientAddressDTO that = (ClientAddressDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return this.id;
    }

    public @NotNull ClientDTO getClient() {
        return this.client;
    }

    public @NotNull String getCountry() {
        return this.country;
    }

    public @NotNull String getCity() {
        return this.city;
    }

    public @NotNull int getPostalCode() {
        return this.postalCode;
    }

    public @NotNull String getStreet() {
        return this.street;
    }

    public @NotNull String getHouse() {
        return this.house;
    }

    public @NotNull int getRoom() {
        return this.room;
    }

    public List<OrderDTO> getOrders() {
        return this.orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(@NotNull ClientDTO client) {
        this.client = client;
    }

    public void setCountry(@NotNull String country) {
        this.country = country;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public void setPostalCode(@NotNull int postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet(@NotNull String street) {
        this.street = street;
    }

    public void setHouse(@NotNull String house) {
        this.house = house;
    }

    public void setRoom(@NotNull int room) {
        this.room = room;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}

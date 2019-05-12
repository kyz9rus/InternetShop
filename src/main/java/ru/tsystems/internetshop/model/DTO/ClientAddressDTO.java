package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is DTO for ClientAddress entity
 */
@Data
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

package ru.tsystems.internetshop.model.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is DTO for client entity
 */
public class ClientDTO {

    private Long id;

    @NotNull(message = "Please enter first name")
    private String firstName;

    @NotNull(message = "Please enter last name")
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    @Email(message = "Email should be valid")
    private String email;

    private Long summaryOrdersPrice = 0L;

    private List<OrderDTO> orders = new ArrayList<>();
    private List<ClientAddressDTO> addresses = new ArrayList<>();
    private List<CouponDTO> coupons = new ArrayList<>();

    public ClientDTO() {
    }

    public ClientDTO(Long id) {
        this.id = id;
    }

    public ClientDTO(@Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public ClientDTO(@NotNull(message = "Please enter first name") String firstName, @NotNull(message = "Please enter last name") String lastName, LocalDate birthday, @Email(message = "Email should be valid") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(id, clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return this.id;
    }

    public @NotNull(message = "Please enter first name") String getFirstName() {
        return this.firstName;
    }

    public @NotNull(message = "Please enter last name") String getLastName() {
        return this.lastName;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public @Email(message = "Email should be valid") String getEmail() {
        return this.email;
    }

    public Long getSummaryOrdersPrice() {
        return this.summaryOrdersPrice;
    }

    public List<OrderDTO> getOrders() {
        return this.orders;
    }

    public List<ClientAddressDTO> getAddresses() {
        return this.addresses;
    }

    public List<CouponDTO> getCoupons() {
        return this.coupons;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(@NotNull(message = "Please enter first name") String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NotNull(message = "Please enter last name") String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setEmail(@Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public void setSummaryOrdersPrice(Long summaryOrdersPrice) {
        this.summaryOrdersPrice = summaryOrdersPrice;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public void setAddresses(List<ClientAddressDTO> addresses) {
        this.addresses = addresses;
    }

    public void setCoupons(List<CouponDTO> coupons) {
        this.coupons = coupons;
    }
}



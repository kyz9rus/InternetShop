package ru.tsystems.internetshop.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.model.entity.Coupon;
import ru.tsystems.internetshop.model.entity.Order;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Data
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

    private Long summaryOrdersPrice;

    private List<OrderDTO> orders = new ArrayList<>();
    private List<ClientAddressDTO> addresses = new ArrayList<>();
    private List<CouponDTO> coupons = new ArrayList<>();

    public ClientDTO() {
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
        return Objects.equals(id, clientDTO.id) &&
                Objects.equals(firstName, clientDTO.firstName) &&
                Objects.equals(lastName, clientDTO.lastName) &&
                Objects.equals(birthday, clientDTO.birthday) &&
                Objects.equals(email, clientDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, email);
    }
}



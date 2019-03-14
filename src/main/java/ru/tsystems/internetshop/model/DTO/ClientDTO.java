package ru.tsystems.internetshop.model.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @NotNull(message = "Please enter password")
    private String password;
    private Set<OrderDTO> orders = new HashSet<>();

    public ClientDTO(@NotNull(message = "Please enter first name") String firstName, @NotNull(message = "Please enter last name") String lastName, LocalDate birthday, @Email(message = "Email should be valid") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }
}



package ru.tsystems.internetshop.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ClientDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;

    public ClientDto() {
    }

    public ClientDto(String firstName, String lastName, LocalDate birthday, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }
}

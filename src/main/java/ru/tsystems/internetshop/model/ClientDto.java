package ru.tsystems.internetshop.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class ClientDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
//    @NotEmpty
    private String firstName;
    @NotNull
//    @NotEmpty
    private String lastName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;
//    @Email
    @NotNull
//    @NotEmpty
    private String email;
    @NotNull
//    @NotEmpty
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

package ru.tsystems.internetshop.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "client")
@Table(name = "client")
public class ClientDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Please enter first name")
    private String firstName;

    @NotNull(message = "Please enter last name")
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    @Email(message = "Email should be valid")
    @Column(unique = true, length = 70)
    private String email;

    @NotNull(message = "Please enter password")
    private String password;

    @OneToMany(mappedBy = "client")
    private Set<OrderDto> orders = new HashSet<>();

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



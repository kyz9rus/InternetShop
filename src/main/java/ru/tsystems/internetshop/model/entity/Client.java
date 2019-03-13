package ru.tsystems.internetshop.model.entity;

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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "SEQ_CLIENT", allocationSize = 1)
    @Column(name = "id")
    private Long id;

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
    private Set<Order> orders = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName, LocalDate birthday, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }
}



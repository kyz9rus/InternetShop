package ru.tsystems.internetshop.model.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;

import javax.persistence.*;
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

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="email", unique = true, referencedColumnName="email")
//    private User user;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientAddress> addresses = new HashSet<>();

    public Client() {
    }
}

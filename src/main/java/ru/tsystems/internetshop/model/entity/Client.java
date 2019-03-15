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

    @Column(unique = true, length = 70)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientAddress> addresses = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName, LocalDate birthday, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

//    @Override
//    public ClientDTO clone() throws CloneNotSupportedException {
//        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setId(id);
//        clientDTO.setBirthday(birthday);
//        clientDTO.setEmail(email);
//        clientDTO.setFirstName(firstName);
//        clientDTO.setLastName(lastName);
//        clientDTO.setPassword(password);
//
//        Set<OrderDTO> orderDTOS = new HashSet<>();
//        for (Order order : orders)
//            orderDTOS.add(order.clone());
//
//        clientDTO.setOrders(orderDTOS);
//
//        Set<ClientAddressDTO> addressDTOs = new HashSet<>();
//        for (ClientAddress clientAddress : addresses)
//            addressDTOs.add(clientAddress.clone());
//
//        clientDTO.setAddresses(addressDTOs);
//
//        return clientDTO;
//    }
}

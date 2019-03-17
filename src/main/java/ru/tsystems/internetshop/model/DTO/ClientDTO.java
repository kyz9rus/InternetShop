package ru.tsystems.internetshop.model.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.model.entity.Order;

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

    private Set<Order> orders = new HashSet<>();
    private Set<ClientAddress> addresses = new HashSet<>();

    public ClientDTO() {
    }

    public ClientDTO(@NotNull(message = "Please enter first name") String firstName, @NotNull(message = "Please enter last name") String lastName, LocalDate birthday, @Email(message = "Email should be valid") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }

//    @Override
//    public Client clone() throws CloneNotSupportedException {
//        Client client = new Client();
//        client.setId(id);
//        client.setLastName(lastName);
//        client.setFirstName(firstName);
//        client.setEmail(email);
//        client.setPassword(password);
//        client.setBirthday(birthday);
//
//        Set<Order> orders = new HashSet<>();
//        for (OrderDTO orderDTO : this.orders)
//            orders.add(orderDTO.clone());
//        client.setOrders(orders);
//
//        Set<ClientAddress> clientAddresses = new HashSet<>();
//        for (ClientAddressDTO clientAddressDTO : this.addresses)
//            clientAddresses.add(clientAddressDTO.clone());
//        client.setAddresses(clientAddresses);
//
//        return client;
//    }
}



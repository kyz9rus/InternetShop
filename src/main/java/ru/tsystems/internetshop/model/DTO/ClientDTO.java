package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String password;
    private Set<OrderDTO> orders = new HashSet<>();
}



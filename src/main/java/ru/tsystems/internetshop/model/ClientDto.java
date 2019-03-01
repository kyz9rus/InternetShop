package ru.tsystems.internetshop.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDto {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String password;
}

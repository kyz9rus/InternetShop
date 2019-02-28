package ru.tsystems.internetshop.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private String name;
    private String password;
}

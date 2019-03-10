package ru.tsystems.internetshop.model;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.tsystems.internetshop.model.ProductDto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "user")
@Table(name = "application_user")
public class User {

    @Id
    @Column(length = 100, name = "email")
    @Email
    private String email;

    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "role"))
    private Set<Role> roles;

    public User() {
    }

    public User(User user) {
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles();
    }
}

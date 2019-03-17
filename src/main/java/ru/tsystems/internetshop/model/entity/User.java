package ru.tsystems.internetshop.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.tsystems.internetshop.model.DTO.UserDTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "user")
@Table(name = "application_user")
public class User implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "SEQ_USER", allocationSize = 1)
    private Long id;

    @Column(length = 100, name = "email", unique = true)
    @Email
    private String email;

    @NotNull
    @JsonIgnore
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(User user) {
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles();
    }

    public User(@Email String email, @NotNull String password, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(@Email String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public UserDTO clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        HashSet<Role> roles = new HashSet<>();
        roles.addAll(user.getRoles());

        userDTO.setRoles(roles);

        return userDTO;
    }
}

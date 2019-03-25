package ru.tsystems.internetshop.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.tsystems.internetshop.model.DTO.UserDTO;

import javax.persistence.*;
import java.io.Serializable;
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
    private String email;

    @JsonIgnore
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String email, String password) {
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

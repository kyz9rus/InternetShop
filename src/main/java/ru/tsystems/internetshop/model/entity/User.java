package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "user")
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "SEQ_USER", allocationSize = 1)
    private Long id;

    @Column(length = 100, name = "email")
    @Email
    private String email;

    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(User user) {
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles();
    }

    public User(@Email String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }

}

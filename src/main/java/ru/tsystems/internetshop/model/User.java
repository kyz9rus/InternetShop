package ru.tsystems.internetshop.model;

import lombok.Data;
import ru.tsystems.internetshop.model.ProductDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "user")
@Table(name = "users")
public class User {

    @Id
    private String login;
    @NotNull
//    @JsonIgnore
    private String password;

//    @OneToMany(mappedBy = "user")
//    @JsonIgnore


//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_login"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public User(User user) {
        login = user.getLogin();
        password = user.getPassword();
//        roles = user.getRoles();
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

//    Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
}

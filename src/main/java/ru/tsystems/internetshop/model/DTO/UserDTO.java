package ru.tsystems.internetshop.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.tsystems.internetshop.model.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class is DTO for user entity
 */
public class UserDTO implements Serializable {

    private Long id;

    @Email
    @NotNull
    private String email;

    @NotNull
    @JsonIgnore
    private String password;

    private Set<Role> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(UserDTO userDTO) {
        email = userDTO.getEmail();
        password = userDTO.getPassword();
        roles = userDTO.getRoles();
    }

    public UserDTO(@Email String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public @Email @NotNull String getEmail() {
        return this.email;
    }

    public @NotNull String getPassword() {
        return this.password;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(@Email @NotNull String email) {
        this.email = email;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserDTO(id=" + this.getId() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", roles=" + this.getRoles() + ")";
    }
}

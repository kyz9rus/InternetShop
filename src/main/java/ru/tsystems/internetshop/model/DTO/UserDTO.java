package ru.tsystems.internetshop.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.tsystems.internetshop.model.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is DTO for user entity
 */
@Data
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

}

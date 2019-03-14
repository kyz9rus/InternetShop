package ru.tsystems.internetshop.model.DTO;

import lombok.Data;
import ru.tsystems.internetshop.model.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO implements Serializable {

    private Long id;

    @Email
    private String email;

    @NotNull
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

package ru.tsystems.internetshop.model.DTO;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * This class is DTO for role entity
 */
public class RoleDTO {
    private Long id;

    @NotNull
    private String role;

    public RoleDTO() {
    }

    public Long getId() {
        return this.id;
    }

    public @NotNull String getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(@NotNull String role) {
        this.role = role;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(id, roleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RoleDTO(id=" + this.getId() + ", role=" + this.getRole() + ")";
    }
}

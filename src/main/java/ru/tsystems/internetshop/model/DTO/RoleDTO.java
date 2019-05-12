package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * This class is DTO for role entity
 */
@Data
public class RoleDTO {
    private Long id;

    @NotNull
    private String role;
}

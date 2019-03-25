package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
public class RoleDTO {
    @NotNull
    private String role;
}

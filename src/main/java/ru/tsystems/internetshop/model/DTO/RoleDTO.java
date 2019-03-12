package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class RoleDTO {
    private String role;
}

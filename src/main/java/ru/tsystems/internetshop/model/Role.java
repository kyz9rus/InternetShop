package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity(name = "role")
@Table(name = "role")
public class Role {
    @Id
    @Column(length = 100, name = "role")
    private String role;

}

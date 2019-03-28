package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, name = "name")
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }


}

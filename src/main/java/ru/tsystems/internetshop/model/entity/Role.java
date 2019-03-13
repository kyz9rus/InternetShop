package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "SEQ_ROLE", allocationSize = 1)
    private Long id;

    @Column(length = 100, name = "name")
    private String name;
}
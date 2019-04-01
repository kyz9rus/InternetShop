package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "coupon")
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(unique = true)
    private String value;

    @NotNull
    private String description;

    @ManyToMany(mappedBy = "coupons")
    private List<Client> clients = new ArrayList<>();
}

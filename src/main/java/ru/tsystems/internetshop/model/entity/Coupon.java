package ru.tsystems.internetshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is coupon entity
 */
@Entity(name = "coupon")
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String value;

    @NotNull
    private String description;

    @ManyToMany(mappedBy = "coupons")
    private List<Client> clients = new ArrayList<>();

    public Coupon() {
    }

    public Integer getId() {
        return this.id;
    }

    public @NotNull String getValue() {
        return this.value;
    }

    public @NotNull String getDescription() {
        return this.description;
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValue(@NotNull String value) {
        this.value = value;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon that = (Coupon) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Coupon(id=" + this.getId() + ", value=" + this.getValue() + ", description=" + this.getDescription() + ", clients=" + this.getClients() + ")";
    }
}

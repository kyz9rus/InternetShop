package ru.tsystems.internetshop.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class is role entity
 */
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


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role that = (Role) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public String toString() {
        return "Role(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}

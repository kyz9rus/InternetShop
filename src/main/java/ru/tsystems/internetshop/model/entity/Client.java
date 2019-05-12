package ru.tsystems.internetshop.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is client entity
 */
@Entity(name = "client")
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    @Column(unique = true)
    private String email;

    @Column(name = "summary_orders_price", columnDefinition = "bigint default 0")
    private Long summaryOrdersPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<ClientAddress> addresses = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "client_coupon",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    )
    private List<Coupon> coupons = new ArrayList<>();

    public Client() {
    }

    public Client(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", summaryOrdersPrice=" + summaryOrdersPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public String getEmail() {
        return this.email;
    }

    public Long getSummaryOrdersPrice() {
        return this.summaryOrdersPrice;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public List<ClientAddress> getAddresses() {
        return this.addresses;
    }

    public List<Coupon> getCoupons() {
        return this.coupons;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSummaryOrdersPrice(Long summaryOrdersPrice) {
        this.summaryOrdersPrice = summaryOrdersPrice;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setAddresses(List<ClientAddress> addresses) {
        this.addresses = addresses;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}

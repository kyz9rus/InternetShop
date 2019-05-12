package ru.tsystems.internetshop.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class is product entity
 */
@Entity(name = "product")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, length = 70)
    private String name;

    private int price;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "category_id", columnDefinition = "bigint references category(id) on delete set null")
    private Category category;

    private double weight;

    private String volume;

    private long quantityInStock;

    @Column(columnDefinition = "text default 'http://promusicayouthchorus.org/wp-content/uploads/2018/07/no-image.jpg'")
    private String imgSrc = "http://promusicayouthchorus.org/wp-content/uploads/2018/07/no-image.jpg";

    @Column(name = "number_of_sales", columnDefinition = "bigint default 0")
    private long numberOfSales;

    public Product() {
    }

    public Product(long id) {
        this.id = id;
    }

    public Product(String name, int price, double weight, String volume, long quantityInStock) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.volume = volume;
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", volume='" + volume + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", imgSrc='" + imgSrc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public Category getCategory() {
        return this.category;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getVolume() {
        return this.volume;
    }

    public long getQuantityInStock() {
        return this.quantityInStock;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }

    public long getNumberOfSales() {
        return this.numberOfSales;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setQuantityInStock(long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setNumberOfSales(long numberOfSales) {
        this.numberOfSales = numberOfSales;
    }
}

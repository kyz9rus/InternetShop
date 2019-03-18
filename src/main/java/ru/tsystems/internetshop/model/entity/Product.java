package ru.tsystems.internetshop.model.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "product")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(unique = true, length = 70)
    private String name;

    @NotNull
    private int price;

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_color",
            joinColumns =  @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "color", referencedColumnName = "name")
    )
    private Set<Color> colors = new HashSet<>();

    private double weight;

    @Size(min = 11, max = 11)
    private String volume;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns  =  @JoinColumn(name = "order_id", referencedColumnName = "id")
    )
    private Set<Order> orders = new HashSet<>();

    @NotNull
    private long quantityInStock;

    public Product() {
    }

    public Product(@NotNull String name, @NotNull int price, double weight, String volume, @NotNull long quantityInStock) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.volume = volume;
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product";
    }

    public Long getId() {
        return this.id;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull int getPrice() {
        return this.price;
    }

    public Category getCategory() {
        return this.category;
    }

    public Set<Color> getColors() {
        return this.colors;
    }

    public double getWeight() {
        return this.weight;
    }

    public @Size(min = 11, max = 11) String getVolume() {
        return this.volume;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public @NotNull long getQuantityInStock() {
        return this.quantityInStock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setPrice(@NotNull int price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setVolume(@Size(min = 11, max = 11) String volume) {
        this.volume = volume;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void setQuantityInStock(@NotNull long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (this.getPrice() != other.getPrice()) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        final Object this$colors = this.getColors();
        final Object other$colors = other.getColors();
        if (this$colors == null ? other$colors != null : !this$colors.equals(other$colors)) return false;
        if (Double.compare(this.getWeight(), other.getWeight()) != 0) return false;
        final Object this$volume = this.getVolume();
        final Object other$volume = other.getVolume();
        if (this$volume == null ? other$volume != null : !this$volume.equals(other$volume)) return false;
        final Object this$orders = this.getOrders();
        final Object other$orders = other.getOrders();
        if (this$orders == null ? other$orders != null : !this$orders.equals(other$orders)) return false;
        if (this.getQuantityInStock() != other.getQuantityInStock()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + this.getPrice();
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final Object $colors = this.getColors();
        result = result * PRIME + ($colors == null ? 43 : $colors.hashCode());
        final long $weight = Double.doubleToLongBits(this.getWeight());
        result = result * PRIME + (int) ($weight >>> 32 ^ $weight);
        final Object $volume = this.getVolume();
        result = result * PRIME + ($volume == null ? 43 : $volume.hashCode());
        final Object $orders = this.getOrders();
        result = result * PRIME + ($orders == null ? 43 : $orders.hashCode());
        final long $quantityInStock = this.getQuantityInStock();
        result = result * PRIME + (int) ($quantityInStock >>> 32 ^ $quantityInStock);
        return result;
    }
}

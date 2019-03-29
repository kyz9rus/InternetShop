package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "product")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, length = 70)
    private String name;

    private int price;

    @ManyToOne(cascade = {CascadeType.DETACH})
//    @ManyToOne
    @JoinColumn(name = "category_id", columnDefinition = "bigint references category(id) on delete set null")
    private Category category;

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "product_color",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "color", referencedColumnName = "name")
    )
    private List<Color> colors = new ArrayList<>();

    private double weight;

    private String volume;

//    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
//    private List<Order> orders = new ArrayList<>();

    private long quantityInStock;

    @Column(columnDefinition = "text default 'http://promusicayouthchorus.org/wp-content/uploads/2018/07/no-image.jpg'")
    private String imgSrc;

    @Column(name = "number_of_sales", columnDefinition = "bigint default 0")
    private long numberOfSales;

    public Product() {
    }

    public Product (String name, int price, double weight, String volume, long quantityInStock) {
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (this.getPrice() != other.getPrice()) return false;
        if (Double.compare(this.getWeight(), other.getWeight()) != 0) return false;
        final Object this$volume = this.getVolume();
        final Object other$volume = other.getVolume();
        if (this$volume == null ? other$volume != null : !this$volume.equals(other$volume)) return false;
        if (this.getQuantityInStock() != other.getQuantityInStock()) return false;
        final Object this$imgSrc = this.getImgSrc();
        final Object other$imgSrc = other.getImgSrc();
        if (this$imgSrc == null ? other$imgSrc != null : !this$imgSrc.equals(other$imgSrc)) return false;
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
        final long $weight = Double.doubleToLongBits(this.getWeight());
        result = result * PRIME + (int) ($weight >>> 32 ^ $weight);
        final Object $volume = this.getVolume();
        result = result * PRIME + ($volume == null ? 43 : $volume.hashCode());
        final long $quantityInStock = this.getQuantityInStock();
        result = result * PRIME + (int) ($quantityInStock >>> 32 ^ $quantityInStock);
        final Object $imgSrc = this.getImgSrc();
        result = result * PRIME + ($imgSrc == null ? 43 : $imgSrc.hashCode());
        return result;
    }
}

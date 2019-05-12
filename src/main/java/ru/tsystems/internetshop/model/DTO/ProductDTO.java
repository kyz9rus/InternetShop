package ru.tsystems.internetshop.model.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is DTO for product entity
 */
public class ProductDTO implements Serializable {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int price;
    private CategoryDTO category;
    private double weight;

    @Size(min = 11, max = 11)
    private String volume;
    private List<OrderDTO> orders = new ArrayList<>();

    @NotNull
    private long quantityInStock;
    private String imgSrc;

    private long numberOfSales;

    public ProductDTO() {
    }

    public ProductDTO(long id, int price) {
        this.id = id;
        this.price = price;
    }

    public void setDefaultImgSrc() {
        this.imgSrc = "http://promusicayouthchorus.org/wp-content/uploads/2018/07/no-image.jpg";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", volume='" + volume + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", imgSrc='" + imgSrc + '\'' +
                ", numberOfSales=" + numberOfSales +
                '}';
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

    public CategoryDTO getCategory() {
        return this.category;
    }

    public double getWeight() {
        return this.weight;
    }

    public @Size(min = 11, max = 11) String getVolume() {
        return this.volume;
    }

    public List<OrderDTO> getOrders() {
        return this.orders;
    }

    public @NotNull long getQuantityInStock() {
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

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setPrice(@NotNull int price) {
        this.price = price;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setVolume(@Size(min = 11, max = 11) String volume) {
        this.volume = volume;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public void setQuantityInStock(@NotNull long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setNumberOfSales(long numberOfSales) {
        this.numberOfSales = numberOfSales;
    }
}

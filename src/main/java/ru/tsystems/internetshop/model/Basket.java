package ru.tsystems.internetshop.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Basket {
    private List<ProductDTO> products;
    private int numberOfProducts;
    private int summaryPrice;

    public Basket() {
        products = new ArrayList<>();
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void addProduct(ProductDTO productDTO) {
        products.add(productDTO);
        numberOfProducts++;
        summaryPrice += productDTO.getPrice();
    }
}
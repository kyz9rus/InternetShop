package ru.tsystems.internetshop.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class Basket {
    private Map<ProductDTO, Integer> products;
    private int numberOfProducts;
    private int summaryPrice;

    public Basket() {
        products = new HashMap<>();
    }

    public Basket(Map<ProductDTO, Integer> products, int numberOfProducts, int summaryPrice) {
        this.products = products;
        this.numberOfProducts = numberOfProducts;
        this.summaryPrice = summaryPrice;
    }

    public Map<ProductDTO, Integer> getProducts() {
        return products;
    }

    public void addProduct(ProductDTO productDTO) {
        if (products.containsKey(productDTO))
            products.put(productDTO, products.get(productDTO) + 1);
        else
            products.put(productDTO, 1);

        numberOfProducts++;
        summaryPrice += productDTO.getPrice();
    }

    public void increaseProduct(ProductDTO productDTO) {
        int numberOfProduct = products.get(productDTO);
        if (numberOfProduct > 1)
            products.put(productDTO, --numberOfProduct);
        else
            products.remove(productDTO);
        numberOfProducts--;

        summaryPrice -= productDTO.getPrice();
    }

    public void removeProduct(ProductDTO productDTO) {
        int numberOfProducts = products.get(productDTO);

        this.numberOfProducts -= numberOfProducts;
        this.summaryPrice -= numberOfProducts * productDTO.getPrice();

        products.remove(productDTO);
    }
}
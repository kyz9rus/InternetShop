package ru.tsystems.internetshop.service.Impl;

import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.service.BasketService;

import java.util.Map;

@Service
public class BasketServiceImpl implements BasketService {

    @Override
    public int calcPrice(Basket basket) {
        Map<ProductDTO, Integer> products = basket.getProducts();
        int price = 0;

        for (ProductDTO productDTO : products.keySet()) {
            int numberProduct = products.get(productDTO);
            for (int i = 0; i < numberProduct; i++) {
                productDTO.setNumberOfSales(productDTO.getNumberOfSales() + 1);
                price += productDTO.getPrice();
            }
        }

        return price;
    }
}

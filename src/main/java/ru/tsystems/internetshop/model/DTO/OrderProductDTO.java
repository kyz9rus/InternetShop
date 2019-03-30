package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

@Data
public class OrderProductDTO {
    private Long id;
    private OrderDTO order;
    private ProductDTO product;
    private int amount;
}

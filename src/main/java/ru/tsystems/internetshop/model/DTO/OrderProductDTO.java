package ru.tsystems.internetshop.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderProductDTO {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private OrderDTO order;
    private ProductDTO product;
    private int amount;
}

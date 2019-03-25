package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class ColorDTO {
    private String name;
    private Set<ProductDTO> products = new HashSet<>();
}

package ru.tsystems.internetshop.model.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.tsystems.internetshop.model.entity.Views;

import java.util.List;

@Data
@JsonView(Views.FullMessage.class)
public class ProductPageDTO {
    private List<ProductDTO> products;
    private int currentPage;
    private int totalPages;
}

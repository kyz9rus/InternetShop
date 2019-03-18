package ru.tsystems.internetshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("busket")
@SessionAttributes(value = "client")
public class BusketController {

    List<ProductDTO> productList;

    public BusketController() {
        productList = new ArrayList<>();
    }

    @PostMapping("put-product")
    public void putProduct(@ModelAttribute("product") ProductDTO productDTO) {
        productList.add(productDTO);
    }
}

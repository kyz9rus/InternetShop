package ru.tsystems.internetshop.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.DTO.*;
import ru.tsystems.internetshop.model.entity.*;

@Component
public class Mapper {

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public ClientDTO convertToDto(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    public OrderDTO convertToDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public CategoryDTO convertToDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }


    public Client convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
}

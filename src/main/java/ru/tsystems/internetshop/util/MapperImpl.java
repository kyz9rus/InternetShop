package ru.tsystems.internetshop.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.DTO.*;
import ru.tsystems.internetshop.model.entity.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Component
public class Mapper<EntityClass, DTOCLass> {

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    private final Class entityClass;
    private final Class dtoClass;

    @SuppressWarnings("unchecked")
    public Mapper() {
        this.entityClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.dtoClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public DTOCLass convertToDTO (EntityClass entity) {
        return modelMapper.map(entity, (Type) dtoClass.getClass());
    }

    public EntityClass convertToEntity (DTOCLass DTO) {
        return modelMapper.map(DTO, (Type) entityClass.getClass());
    }

    public ClientDTO convertToDto(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    public OrderDTO convertToDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public CategoryDTO convertToDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public CouponDTO convertToDto(Coupon coupon) {
        return modelMapper.map(coupon, CouponDTO.class);
    }

    public ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public ClientAddressDTO convertToDto(ClientAddress clientAddress) {
        return modelMapper.map(clientAddress, ClientAddressDTO.class);
    }

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public Client convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

//    public Category convertToEntity(CategoryDTO categoryDTO) {
//        return modelMapper.map(categoryDTO, Category.class);
//    }

    public Order convertToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    public ClientAddress convertToEntity(ClientAddressDTO clientAddressDTO) {
        return modelMapper.map(clientAddressDTO, ClientAddress.class);
    }

    public Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    public OrderProduct convertToEntity(OrderProductDTO orderProductDTO) {
        return modelMapper.map(orderProductDTO, OrderProduct.class);
    }
}

package ru.tsystems.internetshop.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.DTO.*;
import ru.tsystems.internetshop.model.entity.*;

/**
 * This class is implementation of ModelMapper for necessary entities
 */
@Component
public class Mapper {

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    /**
     * This method converts client entity to clientDTO object
     *
     * @param client client entity
     * @return clientDTO object
     */
    public ClientDTO convertToDto(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    /**
     * This method converts order entity to orderDTO object
     *
     * @param order client entity
     * @return orderDTO object
     */
    public OrderDTO convertToDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    /**
     * This method converts category entity to categoryDTO object
     *
     * @param category category entity
     * @return categoryDTO object
     */
    public CategoryDTO convertToDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    /**
     * This method converts coupon entity to couponDTO object
     *
     * @param coupon coupon entity
     * @return couponDTO object
     */
    public CouponDTO convertToDto(Coupon coupon) {
        return modelMapper.map(coupon, CouponDTO.class);
    }

    /**
     * This method converts product entity to productDTO object
     *
     * @param product product entity
     * @return productDTO object
     */
    public ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    /**
     * This method converts clientAddress entity to clientAddressDTO object
     *
     * @param clientAddress clientAddress entity
     * @return clientAddressDTO object
     */
    public ClientAddressDTO convertToDto(ClientAddress clientAddress) {
        return modelMapper.map(clientAddress, ClientAddressDTO.class);
    }

    /**
     * This method converts news entity to newsDTO object
     *
     * @param news news entity
     * @return newsDTO object
     */
    public NewsDTO convertToDto(News news) {
        return modelMapper.map(news, NewsDTO.class);
    }

    /**
     * This method converts clientDTO object to client entity
     *
     * @param clientDTO client (data transfer object)
     * @return client entity
     */
    public Client convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    /**
     * This method converts userDTO object to user entity
     *
     * @param userDTO iser (data transfer object)
     * @return user entity
     */
    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    /**
     * This method converts category object to category entity
     *
     * @param categoryDTO category (data transfer object)
     * @return category entity
     */
    public Category convertToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    /**
     * This method converts orderDTO object to order entity
     *
     * @param orderDTO order (data transfer object)
     * @return oredr entity
     */
    public Order convertToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    /**
     * This method converts clientAddressDTO object to clientAddress entity
     *
     * @param clientAddressDTO clientAddress (data transfer object)
     * @return clientAddress entity
     */
    public ClientAddress convertToEntity(ClientAddressDTO clientAddressDTO) {
        return modelMapper.map(clientAddressDTO, ClientAddress.class);
    }

    /**
     * This method converts productDTO object to product entity
     *
     * @param productDTO product (data transfer object)
     * @return product entity
     */
    public Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}

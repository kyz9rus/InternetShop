package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.User;

/**
 * This interface extends DAO interface and declares special methods for User class
 */
public interface UserDAO extends DAO<User, Long>{
    UserDTO findByEmail(String email);
    void SaveOrUpdateUser(User user);
}
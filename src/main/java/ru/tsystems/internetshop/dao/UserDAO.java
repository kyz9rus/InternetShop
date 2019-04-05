package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.User;

public interface UserDAO extends DAO<User, Long>{
    UserDTO findByEmail(String email);
    void SaveOrUpdateUser(User user);
}
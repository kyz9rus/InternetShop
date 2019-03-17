package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.User;

public interface UserService {
    void saveUser(User user);

    void updateUser(User user);

    UserDTO getUserByEmail(String email);
}

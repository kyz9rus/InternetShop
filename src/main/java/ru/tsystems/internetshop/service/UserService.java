package ru.tsystems.internetshop.service;

import org.springframework.ui.Model;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.User;

/**
 * This is interface, which declares methods for managing users
 */
public interface UserService {
    void saveUser(User user);

    void updateUser(User user);

    UserDTO getUserByEmail(String email);

    Model changePassword(ClientDTO clientDTO, String password, String newPassword, String repeatNewPassword, Model model);
}

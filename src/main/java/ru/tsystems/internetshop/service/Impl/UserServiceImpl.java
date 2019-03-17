package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.dao.UserDAO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.model.entity.User;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.service.UserService;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void saveUser(User user) {
        userDAO.SaveOrUpdateUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}

package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.ProductDto;
import ru.tsystems.internetshop.model.User;

import java.util.List;

public interface UserDAO {
    User findByEmail(String email);
}
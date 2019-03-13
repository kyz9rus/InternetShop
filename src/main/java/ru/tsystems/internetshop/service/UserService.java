package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.dao.DAO;
import ru.tsystems.internetshop.model.entity.User;

public interface UserService {
    void saveUser(User user);
}

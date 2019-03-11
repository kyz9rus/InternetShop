package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.User;

public interface UserDAO {
    User findByEmail(String email);
}
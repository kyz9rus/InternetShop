package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Role;

public interface RoleDAO extends DAO<Role, Long> {
    Role findByName(String name);
}
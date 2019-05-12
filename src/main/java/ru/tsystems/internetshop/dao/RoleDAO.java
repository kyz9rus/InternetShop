package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Role;

/**
 * This interface extends DAO interface and declares special methods for Role class
 */
public interface RoleDAO extends DAO<Role, Long> {
    Role findByName(String name);
}
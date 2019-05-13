package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.entity.Role;

/**
 * This is interface, which declares methods for managing roles
 */
public interface RoleService {
    Role getRoleByName(String name);
}

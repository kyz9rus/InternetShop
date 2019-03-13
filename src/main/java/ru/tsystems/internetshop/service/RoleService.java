package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.entity.Role;

public interface RoleService {
    void saveRole(Role role);

    Role getRoleByName(String name);
}

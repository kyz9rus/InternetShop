package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.entity.Role;

public interface RoleService {
    Role getRoleByName(String name);
}

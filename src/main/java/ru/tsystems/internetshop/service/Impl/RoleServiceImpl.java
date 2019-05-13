package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.RoleDAO;
import ru.tsystems.internetshop.dao.UserDAO;
import ru.tsystems.internetshop.model.entity.Role;
import ru.tsystems.internetshop.model.entity.User;
import ru.tsystems.internetshop.service.RoleService;
import ru.tsystems.internetshop.service.UserService;

/**
 * This is class, which implements methods from RoleService
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    /**
     * This method gets role by name
     * @param name role name
     * @return role
     */
    @Override
    public Role getRoleByName(String name) {
        return roleDAO.findByName(name);
    }
}

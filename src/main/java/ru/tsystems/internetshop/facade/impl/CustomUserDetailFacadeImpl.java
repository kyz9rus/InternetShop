package ru.tsystems.internetshop.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.facade.CustomUserDetailFacade;
import ru.tsystems.internetshop.service.CustomUserDetailService;

/**
 * This class override methods from CustomUserDetailFacade
 */
@Transactional
@Component
public class CustomUserDetailFacadeImpl implements CustomUserDetailFacade {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    /**
     * This method loads user vy username (email)
     *
     * @param email user email (unique)
     * @return user details object
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customUserDetailService.loadUserByUsername(email);
    }
}
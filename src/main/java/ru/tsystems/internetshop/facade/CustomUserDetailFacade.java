package ru.tsystems.internetshop.facade;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailFacade {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}

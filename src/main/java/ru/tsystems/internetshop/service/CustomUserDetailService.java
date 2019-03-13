package ru.tsystems.internetshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.dao.UserDAO;
import ru.tsystems.internetshop.model.entity.CustomUserDetail;
import ru.tsystems.internetshop.model.entity.User;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userDAO.findByEmail(email));

        user.orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return user.map(CustomUserDetail::new).get();
    }
}
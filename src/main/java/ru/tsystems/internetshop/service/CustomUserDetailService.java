package ru.tsystems.internetshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.UserDAO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.CustomUserDetail;
import ru.tsystems.internetshop.model.entity.User;

import java.util.Objects;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserDTO> userDTO = Optional.of(userDAO.findByEmail(email));


//        Optional<UserDTO> userDTO = Optional.empty();
//
//        try {
//            userDTO = Optional.of ((UserDTO) user.clone());
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        Optional<User> user = Optional.ofNullable(userDAO.findByEmail(email));

        userDTO.orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return userDTO.map(CustomUserDetail::new).get();
    }
}
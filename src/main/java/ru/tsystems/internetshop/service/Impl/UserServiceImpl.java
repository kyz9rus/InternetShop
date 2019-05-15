package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.tsystems.internetshop.dao.UserDAO;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.User;
import ru.tsystems.internetshop.service.UserService;

/**
 * This is class, which implements methods from UserService
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserClientFacade userClientFacade;

    /**
     * This method saves user
     *
     * @param user user
     */
    @Override
    public void saveUser(User user) {
        userDAO.SaveOrUpdateUser(user);
    }

    /**
     * This method updates user
     *
     * @param user user
     */
    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }


    /**
     * This method gets user by email
     *
     * @param email user email
     * @return user
     */
    @Override
    public UserDTO getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    /**
     * This method checks that client exists, checks that entered passwords are match and changes password
     * @param clientDTO client
     * @param password old password
     * @param newPassword entered password
     * @param repeatNewPassword repeat entered password
     * @param model for attributes
     * @return model
     */
    @Override
    public Model changePassword(ClientDTO clientDTO, String password, String newPassword, String repeatNewPassword, Model model) {
        UserDTO userDTO = getUserByEmail(clientDTO.getEmail());

        if (new BCryptPasswordEncoder().matches(password, userDTO.getPassword()))
            if (newPassword.equals(repeatNewPassword)) {
                userDTO.setPassword(new BCryptPasswordEncoder().encode(newPassword));
                userClientFacade.updateUser(clientDTO, userDTO);

                model.addAttribute("successMessage", "Password successfully changed");
            } else {
                model.addAttribute("errorMessage", "Entered passwords do not match");
            }
        else
            model.addAttribute("errorMessage", "You entered the wrong password.");

        return model;
    }
}

package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.service.AuthenticationService;
import ru.tsystems.internetshop.service.ClientService;

import java.util.Collection;

/**
 * This is class, which implements methods from AuthenticationService
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private ClientService clientService;

    /**
     * This method returns current autetication
     *
     * @return authentication from application context
     */
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * This method gets client from authentication object in application context
     *
     * @return client
     */
    @Override
    public ClientDTO getClient() {
        ClientDTO clientDTO = new ClientDTO();

        Authentication authentication = getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!authorities.isEmpty())
            for (GrantedAuthority grantedAuthority : authorities)
                if (grantedAuthority.getAuthority().equals("ROLE_CLIENT")) {
                    UserDTO userDTO;
                    User user;
                    try {
                        userDTO = (UserDTO) authentication.getPrincipal();
                        clientDTO = clientService.getClientByEmail(userDTO.getEmail());
                    } catch (ClassCastException e) {
                        user = (User) authentication.getPrincipal();
                        clientDTO = clientService.getClientByEmail(user.getUsername());
                    }
                }

        return clientDTO;
    }
}

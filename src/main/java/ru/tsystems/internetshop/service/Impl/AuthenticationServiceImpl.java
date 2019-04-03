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
import ru.tsystems.internetshop.util.Mapper;

import java.util.Collection;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private Mapper mapper;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public ClientDTO getClient() {
        ClientDTO clientDTO = new ClientDTO();

        Authentication authentication = getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!authorities.isEmpty())
            for (GrantedAuthority grantedAuthority : authorities)
                if (grantedAuthority.getAuthority().equals("ROLE_CLIENT")) {
                    UserDTO user = (UserDTO) authentication.getPrincipal();

                    clientDTO = clientService.getClientByEmail(user.getEmail());
                }

        return clientDTO;
    }
}

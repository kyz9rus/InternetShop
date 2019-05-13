package ru.tsystems.internetshop.service;

import org.springframework.security.core.Authentication;
import ru.tsystems.internetshop.model.DTO.ClientDTO;

/**
 * This is interface, which declares methods for client authentication
 */
public interface AuthenticationService {
    Authentication getAuthentication();

    ClientDTO getClient();
}

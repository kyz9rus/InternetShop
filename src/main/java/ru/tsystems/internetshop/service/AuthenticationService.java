package ru.tsystems.internetshop.service;

import org.springframework.security.core.Authentication;
import ru.tsystems.internetshop.model.DTO.ClientDTO;

public interface AuthenticationService {
    Authentication getAuthentication();

    ClientDTO getClient();
}

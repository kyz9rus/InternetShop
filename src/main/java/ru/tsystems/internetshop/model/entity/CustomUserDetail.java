package ru.tsystems.internetshop.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.tsystems.internetshop.model.DTO.UserDTO;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This class is custom user detail which override methods from UserDetails interface
 */
public class CustomUserDetail extends UserDTO implements UserDetails {

    public CustomUserDetail(final UserDTO user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * This method gets password
     *
     * @return password
     */
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    /**
     * This method gets user email
     *
     * @return user email
     */
    @Override
    public String getUsername() {
        return super.getEmail();
    }

    /**
     * This method checks user account non expired
     *
     * @return boolean value
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * This method checks user account non locked
     *
     * @return boolean value
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * This method checks user credentials non expired
     *
     * @return boolean value
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * This method checks user enable
     *
     * @return boolean value
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
package com.gmail.derevets.artem.authservice.service.impl;


import com.gmail.derevets.artem.authservice.exception.UserNotFoundException;
import com.gmail.derevets.artem.authservice.model.User;
import com.gmail.derevets.artem.authservice.model.UserDetailsEntity;
import com.gmail.derevets.artem.authservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This class loads the requested user by using a Spring Data JPA repository.
 *
 * @author Artem Derevets
 */
@Slf4j
@Service
public class RepositoryUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository repository;

    /**
     * Loads the user information.
     *
     * @param username The username of the requested user.
     * @return The information of the user.
     * @throws UsernameNotFoundException Thrown if no user is found with the given username.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username: {}", username);
        Optional<User> optionalUser = repository.findByEmail(username);
        return optionalUser.map(this::userDetailsEntity)
                .orElseThrow(() -> new UserNotFoundException("User Not Found: " + username));
    }

    private UserDetailsEntity userDetailsEntity(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        UserDetailsEntity userDetailsEntity = UserDetailsEntity.builder()
                .email(user.getEmail())
                .id(user.getId())
                .role(user.getRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .authorities(authorities)
                .isAccountNonExpired(user.getIsAccountNonExpired())
                .isAccountNonLocked(user.getIsAccountNonLocked())
                .isCredentialsNonLocked(user.getIsCredentialsNonLocked())
                .isEnabled(user.getIsEnabled())
                .build();
        log.info("Returning user details: {}", userDetailsEntity);
        return userDetailsEntity;
    }
}

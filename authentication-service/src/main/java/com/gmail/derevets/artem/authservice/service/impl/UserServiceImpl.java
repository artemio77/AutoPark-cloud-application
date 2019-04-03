package com.gmail.derevets.artem.authservice.service.impl;

import com.gmail.derevets.artem.authservice.exception.UserNotFoundException;
import com.gmail.derevets.artem.authservice.model.User;
import com.gmail.derevets.artem.authservice.model.enums.Role;
import com.gmail.derevets.artem.authservice.repository.UserRepository;
import com.gmail.derevets.artem.authservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User registerNewAccount(final User user) {
        log.info("CREATE USER {}", user);
        User newUser = User.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonLocked(true)
                .isEnabled(false)
                .role(user.getRole())
                .build();
        newUser.setVerificationCode(generateUniqueVerificationCode());
        return userRepository.save(newUser);

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(UUID id) {
        return userRepository.findById(id).orElse(User.builder().build());
    }

    @Override
    public List<User> findDrivers() {
        return userRepository.findByRoleEquals(Role.ROLE_DRIVER);
    }

    @Override
    public List<User> findManagers() {
        return userRepository.findByRoleEquals(Role.ROLE_MANAGER);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User activateUser(Long code) {
        User user =
                userRepository.findByEmail(userRepository.checkUniqueVerificationCode(code));
        if (!user.getIsEnabled()) {
            user.setIsEnabled(true);
            user.setVerificationCode(null);
            userRepository.save(user);
        }
        return user;
    }

    public String checkUserVerificationCode(Long code) {
        return userRepository.checkUniqueVerificationCode(code);
    }

    private Long generateUniqueVerificationCode() {
        for (; ; ) {
            Random r = new Random();
            Long code = r.longs(1, 10000, 99999).findFirst().getAsLong();
            log.info(code.toString());
            if (userRepository.checkUniqueVerificationCode(new Random()
                    .longs(1, 10000, 99999).findAny().getAsLong()) == null) {
                return code;
            }
        }
    }


}

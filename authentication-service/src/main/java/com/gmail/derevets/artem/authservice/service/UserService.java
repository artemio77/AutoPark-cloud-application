package com.gmail.derevets.artem.authservice.service;

import com.gmail.derevets.artem.authservice.model.User;
import com.gmail.derevets.artem.authservice.model.enums.Role;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User registerNewAccount(final User user);

    User findUserByEmail(final String email);

    User findUserById(final UUID id);

    List<User> findDrivers();

    void updateUser(User user);

    User activateUser(Long verificationCode);

    String checkUserVerificationCode(Long verificationCode);

}

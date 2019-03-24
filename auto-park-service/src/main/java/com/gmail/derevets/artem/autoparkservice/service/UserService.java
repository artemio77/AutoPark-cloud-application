package com.gmail.derevets.artem.autoparkservice.service;

import com.gmail.derevets.artem.autoparkservice.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {


    List<User> getFreeDrivers(List<User> driverList);
}

package com.gmail.derevets.artem.autoparkservice.service;

import com.gmail.derevets.artem.autoparkservice.model.User;
import com.gmail.derevets.artem.autoparkservice.model.enums.Role;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {


    List<User> getFreeDrivers(List<User> driverList);

    Map<String, List<Role>> getRoleList();
}

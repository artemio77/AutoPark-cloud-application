package com.gmail.derevets.artem.autoparkservice.service.impl;

import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.User;
import com.gmail.derevets.artem.autoparkservice.model.enums.Role;
import com.gmail.derevets.artem.autoparkservice.model.enums.TransportType;
import com.gmail.derevets.artem.autoparkservice.service.TransportService;
import com.gmail.derevets.artem.autoparkservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TransportService transportService;


    @Override
    public List<User> getFreeDrivers(List<User> driverList) {

        log.info("GET user {}", driverList);
        List<Transport> transports = transportService.getTransportWithAssingUser();
        log.info("TRANSPORT {}", transports);
        return driverList
                .stream()
                .filter(user -> transports.stream().noneMatch(transport -> transport.getCurrentAssignUser().getId().equals(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Role>> getRoleList() {
        return Collections.singletonMap("value", Arrays.asList(Role.values()));
    }
}

package com.gmail.derevets.artem.autoparkservice.controller;

import com.gmail.derevets.artem.autoparkservice.client.UserClient;
import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.User;
import com.gmail.derevets.artem.autoparkservice.model.enums.Role;
import com.gmail.derevets.artem.autoparkservice.service.TransportService;
import com.gmail.derevets.artem.autoparkservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user-management-service")
public class UserFeingController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserService userService;

    @PutMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        log.info("POST user {}", user);
        return userClient.createUser(user);
    }

    @PostMapping("/activate/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User activateUser(@PathVariable Long code) {
        log.info("POST activate user");
        User user = userClient.activateUser(code);
        log.info("Activated user {}", user);
        return user;
    }

    @PostMapping("/get")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User getUser(@RequestParam("user-id") UUID id) {
        log.info("POST user by id {}", id);
        User user = userClient.getUserById(id);
        log.info("GET user {}", user);
        return user;
    }


    @PostMapping("/get/email")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User getUserByEmail(@RequestParam("email") String email) {
        log.info("POST email {}", email);
        User user = userClient.getUserByEmail(email);
        log.info("GET user {}", user);
        return user;
    }


    @GetMapping("/get/drivers")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<User> getFreeDriverByEmail() {
        log.info("POST email {}");
        List<User> driverList = userClient.getDrivers();
        return userService.getFreeDrivers(driverList);
    }
}
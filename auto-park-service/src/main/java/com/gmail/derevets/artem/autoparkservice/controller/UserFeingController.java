package com.gmail.derevets.artem.autoparkservice.controller;

import com.gmail.derevets.artem.autoparkservice.client.UserClient;
import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.User;
import com.gmail.derevets.artem.autoparkservice.model.enums.Role;
import com.gmail.derevets.artem.autoparkservice.model.enums.TransportType;
import com.gmail.derevets.artem.autoparkservice.service.RouteService;
import com.gmail.derevets.artem.autoparkservice.service.TransportService;
import com.gmail.derevets.artem.autoparkservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-management-service")
public class UserFeingController {

    private final UserClient userClient;

    private final UserService userService;

    private final TransportService transportService;

    private final RouteService routeService;

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


    @GetMapping("/get/all-managers")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<User> getManagerList() {
        return userClient.getManagers();
    }

    @PostMapping("/get/email-check")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Boolean existEmailCheck(@RequestParam("email") String email) {
        Boolean state = userClient.emailExistCheck(email);
        log.info("IS EMAIL EXIST {}", state);
        return state;
    }


    @GetMapping("/get/all-drivers")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<User> getDriverList() {
        return userClient.getDrivers();
    }

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<User> getUserList() {
        return userClient.getUserList();
    }

    @GetMapping("/get/role-list")
    @ResponseStatus(HttpStatus.OK)
    private @ResponseBody
    Map<String, List<Role>> getUserRoleList() {
        return userService.getRoleList();
    }


    @GetMapping("/get/driver-transport")
    private Transport getDriverTransport(@RequestParam("email") String email) {
        User user = userClient.getUserByEmail(email);
        if (Role.ROLE_MANAGER.equals(user.getRole())) {
            throw new RuntimeException("Unexpected user role" + user.getRole());
        }
        return transportService.getTransportByUser(user);
    }

    @GetMapping("/get/driver-route")
    private Route getDriverRoute(@RequestParam("email") String email) {
        User user = userClient.getUserByEmail(email);
        if (Role.ROLE_MANAGER.equals(user.getRole())) {
            throw new RuntimeException("Unexpected user role" + user.getRole());
        }
        return routeService.getRouteByUser(user);
    }

}
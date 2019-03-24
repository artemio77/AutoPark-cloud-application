package com.gmail.derevets.artem.authservice.controller;


import com.gmail.derevets.artem.authservice.model.User;
import com.gmail.derevets.artem.authservice.model.enums.Role;
import com.gmail.derevets.artem.authservice.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping({"/authManagement"})
@RestController
public class UserController {

    private final UserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    private final TokenStore tokenStore;


    @Autowired
    public UserController(UserServiceImpl userService,
                          PasswordEncoder passwordEncoder,
                          TokenStore tokenStore) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenStore = tokenStore;
    }


    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PutMapping("/register")
    public @ResponseBody
    User registerUser(@RequestBody User user) {
        return userService.registerNewAccount(user);
    }

    @PostMapping(path = "/activate/{code}")
    public @ResponseBody
    User activateUser(@PathVariable("code") Long code) {
        log.info(code.toString());
        return userService.activateUser(code);
    }

    @GetMapping("/exist/{email:.+}")
    public Boolean existUserByEmail(@PathVariable String email) {
        if (userService.findUserByEmail(email) != null) {
            log.info("true");
            return true;
        }
        log.info("false");
        return false;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<User> getUserById(@RequestParam("user-id") UUID id) {
        User user = userService.findUserById(id);
        log.info("User,{}", user.toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping(value = "/get/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        log.info("User,{}", user.toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/get/drivers")
    public ResponseEntity<List<User>> getDrivers() {
        List<User> userList = userService.findDrivers();
        log.info("User,{}", userList);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @GetMapping(value = "/getToken/{email:.+}")
    public ResponseEntity<String> getUserToken(@PathVariable("email") String email) {
        Map<String, String> userTokenStore = new HashMap<>();
        String token = tokenStore.findTokensByClientIdAndUserName("fooClientIdPassword", email)
                .stream()
                .map(OAuth2AccessToken::getValue).collect(Collectors.toList()).get(0);
        log.info(token);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

}

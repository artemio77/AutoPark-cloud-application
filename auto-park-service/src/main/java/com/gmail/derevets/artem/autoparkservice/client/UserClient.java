package com.gmail.derevets.artem.autoparkservice.client;

import com.gmail.derevets.artem.autoparkservice.model.User;
import com.gmail.derevets.artem.autoparkservice.model.enums.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "auth-service")
public interface UserClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/auth-service/authManagement/register",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User createUser(@RequestBody User user);


    @RequestMapping(method = RequestMethod.POST, value = "/auth-service/authManagement/activate/{code}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User activateUser(@PathVariable Long code);


    @RequestMapping(method = RequestMethod.GET, value = "/auth-service/authManagement/get",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User getUserById(@RequestParam("user-id") UUID id);

    @RequestMapping(method = RequestMethod.GET, value = "/auth-service/authManagement/get/email",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User getUserByEmail(@RequestParam("email") String email);

    @RequestMapping(method = RequestMethod.GET, value = "/auth-service/authManagement/get/drivers",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<User> getDrivers();

    @RequestMapping(method = RequestMethod.GET, value = "/auth-service/authManagement/get/managers",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<User> getManagers();

    @RequestMapping(method = RequestMethod.GET, value = "/auth-service/authManagement/get/list",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<User> getUserList();


    @RequestMapping(method = RequestMethod.POST, value = "/auth-service/authManagement/exist/{email:.+}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Boolean emailExistCheck(@PathVariable String email);
}

package com.gmail.derevets.artem.autoparkservice.controller;

import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.enums.TransportType;
import com.gmail.derevets.artem.autoparkservice.service.TransportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transport-service")
public class TransportRestController {

    private final TransportService transportService;


    @GetMapping("/get/transport")
    @ResponseStatus(HttpStatus.OK)
    private List<Transport> getTransport() {
        return transportService.getTransportList();
    }


    @GetMapping("/get/empty-user")
    @ResponseStatus(HttpStatus.OK)
    private List<Transport> getTransportWithoutUser() {
        return transportService.getTransportWithoutAssingUser();
    }


    @GetMapping("/get/empty-route")
    @ResponseStatus(HttpStatus.OK)
    private @ResponseBody
    List<Transport> getTransportWithoutRoute() {
        return transportService.getTransportWithoutAssingRoute();
    }

    @GetMapping("/get/with-drivers")
    @ResponseStatus(HttpStatus.OK)
    private @ResponseBody
    List<Transport> getTransportWithDriver() {
        return transportService.getTransportWithAssingUser();
    }

    @GetMapping("/get/with-route")
    @ResponseStatus(HttpStatus.OK)
    private @ResponseBody
    List<Transport> getTransportWithRoute() {
        return transportService.getTransportWithAssingRoute();
    }

    @GetMapping("/get/type-list")
    @ResponseStatus(HttpStatus.OK)
    private @ResponseBody
    Map<String, List<TransportType>> getTransportTypeList() {
        return transportService.getTransportTypeList();
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    private @ResponseBody
    Transport createTransport(@RequestBody Map<String, String> transport) {

        return transportService.createTransport(transport);
    }

    @PatchMapping("/assign-driver")
    @ResponseStatus(HttpStatus.OK)
    private @ResponseBody
    void assignDriver(@RequestParam("email") String email,
                      @RequestParam("id") String id) {
        log.info("EMAIL ASSIGN {}", email);
        log.info("ID ASSIGN {}", id);
        transportService.assignDriver(UUID.fromString(id), email);
    }


}

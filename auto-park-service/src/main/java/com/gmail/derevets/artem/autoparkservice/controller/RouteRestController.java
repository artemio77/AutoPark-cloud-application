package com.gmail.derevets.artem.autoparkservice.controller;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/route-service")
public class RouteRestController {


    @Autowired
    private RouteService routeService;

    @GetMapping("/get")
    public @ResponseBody
    Route getRoute(@RequestParam("routeId") UUID id) {
        return routeService.getRoute(id);
    }


    @GetMapping("/get/route-transport")
    public @ResponseBody
    List<Route> filterRouteWithTransport() {
        return routeService.getRouteListWithTransport();
    }

    @GetMapping("/get/route-with-drivers")
    public @ResponseBody
    List<Route> filterRouteWithDrivers() {
        return routeService.getRouteListWithDrivers();
    }

    @GetMapping("/get/route-transport-empty")
    public @ResponseBody
    List<Route> filterRoute() {
        return routeService.getRouteListWithoutTransport();
    }

    @GetMapping("/get/route")
    public @ResponseBody
    List<Route> getRouteList() {

        List<Route> routeList = routeService.getRouteListWithoutFilter();
//        log.info("Route GET list without filter {}", routeList);
        return routeList;
    }

    @PatchMapping("/assign-transport")
    @ResponseStatus(HttpStatus.OK)
    public Route assignTransport(@RequestParam("transportId") String transportId,
                                 @RequestParam("routeId") String routeId) {
        return routeService.assignTransport(UUID.fromString(transportId), UUID.fromString(routeId));
    }

    @PatchMapping("/remove-assign-transport")
    @ResponseStatus(HttpStatus.OK)
    public Route removeAssignTransport(@RequestParam("transportId") String transportId,
                                       @RequestParam("routeId") String routeId) {
        return routeService.removeAssignTransport(UUID.fromString(transportId), UUID.fromString(routeId));
    }

    @PostMapping("/create")
    public @ResponseBody
    Route createRoute(@RequestBody Route route) {
        return routeService.createRoute(route);
    }

}

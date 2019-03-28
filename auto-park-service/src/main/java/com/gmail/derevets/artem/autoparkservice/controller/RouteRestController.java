package com.gmail.derevets.artem.autoparkservice.controller;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/route-service")
public class RouteRestController {


    @Autowired
    private RouteService routeService;

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


    @PostMapping("/create")
    public @ResponseBody
    Route createRoute(@RequestBody Route route) {
        return routeService.createRoute(route);
    }

}

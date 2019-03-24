package com.gmail.derevets.artem.autoparkservice.controller;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/route-management")
public class RouteRestController {


    @Autowired
    private RouteService routeService;

    @GetMapping("/get/route-transport-empty")
    public @ResponseBody
    List<Route> filterRoute() {
        return routeService.getRouteListWithoutTransport();
    }


    @PostMapping("/create")
    public @ResponseBody
    Route createRoute(@RequestBody Route route) {
        return routeService.createRoute(route);
    }

}

package com.gmail.derevets.artem.autoparkservice.service;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RouteService {

    Route getRoute(UUID id);

    Route getRouteByUser(User user);

    List<Route> getRouteListWithoutTransport();

    List<Route> getRouteListWithDrivers();

    List<Route> getRouteListWithTransport();

    List<Route> getRouteListWithoutFilter();

    Route createRoute(Route route);

    Route assignTransport(UUID transportId, UUID routeId);

    Route removeAssignTransport(UUID transportId, UUID routeId);

}

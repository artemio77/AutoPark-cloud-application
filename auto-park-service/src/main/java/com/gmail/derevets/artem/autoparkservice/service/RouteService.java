package com.gmail.derevets.artem.autoparkservice.service;

import com.gmail.derevets.artem.autoparkservice.model.Route;

import java.util.List;
import java.util.Map;

public interface RouteService {

    List<Route> getRouteListWithoutTransport();

    List<Route> getRouteListWithoutFilter();

    Route createRoute(Route route);

}

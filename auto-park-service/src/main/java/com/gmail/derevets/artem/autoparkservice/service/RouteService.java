package com.gmail.derevets.artem.autoparkservice.service;

import com.gmail.derevets.artem.autoparkservice.model.Route;

import java.util.List;
import java.util.Map;

public interface RouteService {

    List<Route> getRouteListWithoutTransport();


    Route createRoute(Route route);

}

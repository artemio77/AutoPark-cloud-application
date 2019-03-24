package com.gmail.derevets.artem.autoparkservice.service.impl;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.repository.RouteRepository;
import com.gmail.derevets.artem.autoparkservice.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;


    @Override
    public List<Route> getRouteListWithoutTransport() {
        List<Route> emptyTransportRouteList = routeRepository.findByTransportOnRouteListIsNull();
        log.info("Route without transport {}", emptyTransportRouteList);
        return emptyTransportRouteList;
    }

    @Override
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }
}

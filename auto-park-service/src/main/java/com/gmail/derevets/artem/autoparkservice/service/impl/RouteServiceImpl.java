package com.gmail.derevets.artem.autoparkservice.service.impl;

import com.gmail.derevets.artem.autoparkservice.exception.route.RouteNotFoundException;
import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.User;
import com.gmail.derevets.artem.autoparkservice.repository.RouteRepository;
import com.gmail.derevets.artem.autoparkservice.repository.TransportRepository;
import com.gmail.derevets.artem.autoparkservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    private final TransportRepository transportRepository;

    @Override
    public Route getRoute(UUID id) {
        return routeRepository.findById(id).orElseThrow(RouteNotFoundException::new);
    }

    @Override
    public Route getRouteByUser(User user) {
        return routeRepository.findByUsersIn(user).orElseThrow(RouteNotFoundException::new);
    }

    @Override
    public List<Route> getRouteListWithoutTransport() {
        List<Route> emptyTransportRouteList = routeRepository.findAllByTransportIsNull();
        log.info("Route without transport {}", emptyTransportRouteList);
        return emptyTransportRouteList;
    }


    @Override
    @Transactional
    public List<Route> getRouteListWithDrivers() {
        List<UUID> routerWithDrivers = routeRepository.findAllByTransportIsNotNull()
                .stream()
                .filter(route -> route.getTransport().stream().allMatch(transport -> transport.getCurrentAssignUser() != null))
                .map(Route::getId)
                .collect(Collectors.toList());

        return routeRepository.findAllByIdIn(routerWithDrivers);
    }

    @Override
    public List<Route> getRouteListWithTransport() {
        return routeRepository.findAllByTransportIsNotNull();
    }

    @Override
    public List<Route> getRouteListWithoutFilter() {
        return routeRepository.findAll();
    }

    @Override
    @Transactional
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    @Transactional
    public Route assignTransport(UUID transportId, UUID routeId) {
        transportRepository.assignRouteOnTransport(routeId, transportId);
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route " + routeId + "not found"));
    }

    @Override
    @Transactional
    public Route removeAssignTransport(UUID transportId, UUID routeId) {
        transportRepository.removeAssignRouteOnTransport(transportId);
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route " + routeId + "not found"));
    }
}

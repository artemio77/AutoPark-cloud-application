package com.gmail.derevets.artem.autoparkservice.tools;

import com.gmail.derevets.artem.autoparkservice.service.RouteService;
import com.gmail.derevets.artem.autoparkservice.service.TransportService;
import com.gmail.derevets.artem.autoparkservice.service.impl.RouteServiceImpl;
import com.gmail.derevets.artem.autoparkservice.service.impl.TransportServiceImpl;

import static com.gmail.derevets.artem.autoparkservice.tools.MockClient.getUserClient;
import static com.gmail.derevets.artem.autoparkservice.tools.MockRepository.getRouteRepository;
import static com.gmail.derevets.artem.autoparkservice.tools.MockRepository.getTransportRepository;

public class MockService {

    private static TransportService transportService;
    private static RouteService routeService;

    public static TransportService getTransportService() {
        if (transportService == null) {
            transportService = initTransportService();
        }
        return transportService;
    }


    public static RouteService getRoutetService() {
        if (routeService == null) {
            routeService = initRouteService();
        }
        return routeService;
    }

    private static RouteService initRouteService() {
        return new RouteServiceImpl(getRouteRepository(), getTransportRepository());
    }

    private static TransportServiceImpl initTransportService() {
        return new TransportServiceImpl(getTransportRepository(), getUserClient());
    }

}

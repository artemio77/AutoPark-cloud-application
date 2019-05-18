package com.gmail.derevets.artem.autoparkservice.tools;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.User;
import com.gmail.derevets.artem.autoparkservice.repository.RouteRepository;
import com.gmail.derevets.artem.autoparkservice.repository.TransportRepository;
import org.assertj.core.util.Lists;
import org.mockito.ArgumentMatchers;


import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MockRepository {


    private static RouteRepository routeRepository;
    private static TransportRepository transportRepository;


    public static RouteRepository getRouteRepository() {
        if (routeRepository == null) {
            routeRepository = initRouteRepository();
        }
        return routeRepository;
    }

    public static TransportRepository getTransportRepository() {
        if (transportRepository == null) {
            transportRepository = initTransportRepository();
        }
        return transportRepository;
    }

    private static TransportRepository initTransportRepository() {
        TransportRepository transportRepository = mock(TransportRepository.class);
        when(transportRepository.findAllByCurrentAssignUserIsNotNull()).thenReturn(Lists.newArrayList(MockModel.getTransport1()));
        when(transportRepository.findAllByCurrentAssignUserIsNull()).thenReturn(Lists.newArrayList(MockModel.getTransport2()));
        when(transportRepository.findAllByCurrentRouteAssignIsNotNull()).thenReturn(Lists.newArrayList(MockModel.getTransport1()));
        when(transportRepository.findAllByCurrentRouteAssignIsNull()).thenReturn(Lists.newArrayList(MockModel.getTransport2()));
        when(transportRepository.findByCurrentAssignUser(any(User.class))).thenReturn(Optional.ofNullable(MockModel.getTransport1()));
        when(transportRepository.save(any(Transport.class))).thenReturn(MockModel.getTransport1());
        when(transportRepository.findAll()).thenReturn(Lists.newArrayList(MockModel.getTransport1(), MockModel.getTransport2()));
        doAnswer(invocationOnMock -> {
            MockModel.getTransport2().setCurrentAssignUser(MockModel.getDriver());
            return null;
        }).when(transportRepository).assignDriverOnTransport(any(UUID.class), any(UUID.class));
        return transportRepository;
    }

    private static RouteRepository initRouteRepository() {
        RouteRepository routeRepository = mock(RouteRepository.class);
        when(routeRepository.save(any(Route.class))).thenReturn(MockModel.getRouteWithTransport());
        when(routeRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(MockModel.getRouteWithTransport()));
        when(routeRepository.findByUsersIn(any(User.class))).thenReturn(Optional.ofNullable(MockModel.getRouteWithTransport()));
        when(routeRepository.findAllByTransportIsNotNull()).thenReturn(Lists.newArrayList(MockModel.getRouteWithTransport()));
        when(routeRepository.findAllByTransportIsNull()).thenReturn(Lists.newArrayList(MockModel.getRouteWithOutTransport()));
        when(routeRepository.findAllByIdIn(any())).thenReturn(Lists.newArrayList(MockModel.getRouteWithTransport()));
        when(routeRepository.findAll()).thenReturn(Lists.newArrayList(MockModel.getRouteWithTransport(), MockModel.getRouteWithOutTransport()));
        return routeRepository;
    }
}

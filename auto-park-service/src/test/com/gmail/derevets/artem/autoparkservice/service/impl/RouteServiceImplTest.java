package com.gmail.derevets.artem.autoparkservice.service.impl;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.service.RouteService;
import com.gmail.derevets.artem.autoparkservice.tools.MockModel;
import com.gmail.derevets.artem.autoparkservice.tools.MockService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(MockitoJUnitRunner.Silent.class)
public class RouteServiceImplTest {


    private static RouteService routeService;


    @Before
    public void setUp() {
        routeService = MockService.getRoutetService();
    }

    @Test
    public void getRoute() {
        log.info("ID {}", MockModel.idRouteWithTransport);
        log.info("Entity {}", MockModel.getRouteWithTransport());
        Route route = routeService.getRoute(MockModel.idRouteWithTransport);
        assertEquals(MockModel.getRouteWithTransport(), route);
    }

    @Test
    public void getRouteByUser() {
        Route route = routeService.getRouteByUser(MockModel.getDriver());
        assertEquals(MockModel.getRouteWithTransport(), route);
    }

    @Test
    public void getRouteListWithoutTransport() {
        MockModel.getRouteWithOutTransport().setTransport(null);
        List<Route> route = routeService.getRouteListWithoutTransport();
        assertNull(route.iterator().next().getTransport());
        assertEquals(Lists.newArrayList(MockModel.getRouteWithOutTransport()), route);
    }

    @Test
    public void getRouteListWithDrivers() {
        List<Route> routeList = routeService.getRouteListWithDrivers();
        assertEquals(ImmutableList.of(MockModel.getRouteWithTransport()), routeList);
    }

    @Test
    public void getRouteListWithTransport() {
        List<Route> routeList = routeService.getRouteListWithDrivers();
        assertEquals(ImmutableList.of(MockModel.getRouteWithTransport()), routeList);
    }

    @Test
    public void getRouteListWithoutFilter() {
        List<Route> routeList = routeService.getRouteListWithoutFilter();
        assertEquals(ImmutableList.of(MockModel.getRouteWithTransport(), MockModel.getRouteWithOutTransport()), routeList);
    }

    @Test
    public void createRoute() {
        Route route = routeService.createRoute(MockModel.getRouteWithTransport());
        assertEquals(MockModel.getRouteWithTransport(), route);
    }

    @Test
    public void assignTransport() {
        routeService.assignTransport(MockModel.getTransport1().getId(), MockModel.getRouteWithTransport().getId());
        assertNotNull(MockModel.getRouteWithTransport());
        assertEquals(Sets.newHashSet(MockModel.getTransport1()), MockModel.getRouteWithTransport().getTransport());
    }

    @Test
    public void removeAssignTransport() {
        routeService.removeAssignTransport(MockModel.getTransport1().getId(), MockModel.getRouteWithOutTransport().getId());
        assertNull(MockModel.getRouteWithOutTransport().getTransport());
    }
}
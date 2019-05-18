package com.gmail.derevets.artem.autoparkservice.service.impl;


import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.enums.TransportType;
import com.gmail.derevets.artem.autoparkservice.service.TransportService;
import com.gmail.derevets.artem.autoparkservice.tools.MockModel;
import com.gmail.derevets.artem.autoparkservice.tools.MockService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@Slf4j
@RunWith(MockitoJUnitRunner.Silent.class)
public class TransportServiceImplTest {

    private static TransportService transportService;

    @Before
    public void setUp() {
        transportService = MockService.getTransportService();
    }

    @Test
    public void getTransportByUser() {
        Transport transport = transportService.getTransportByUser(MockModel.getDriver());
        assertNotNull(transport);
        assertEquals(MockModel.getTransport1(), transport);

    }

    @Test
    public void getTransportList() {
        List<Transport> transportList = transportService.getTransportList();
        assertNotNull(transportList);
        assertEquals(Lists.newArrayList(MockModel.getTransport1(), MockModel.getTransport2()), transportList);

    }

    @Test
    public void getTransportWithoutAssingUser() {
        List<Transport> transportList = transportService.getTransportWithoutAssingUser();
        assertNotNull(transportList);
        assertNull(transportList.iterator().next().getCurrentAssignUser());
        assertEquals(Lists.newArrayList(MockModel.getTransport2()), transportList);
    }

    @Test
    public void getTransportWithoutAssingRoute() {
        List<Transport> transportList = transportService.getTransportWithoutAssingRoute();
        assertNotNull(transportList);
        assertNull(transportList.iterator().next().getCurrentRouteAssign());
        assertEquals(Lists.newArrayList(MockModel.getTransport2()), transportList);
    }

    @Test
    public void createTransport() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("currentAssignUser", "AssignDriver@com.com");
        requestMap.put("transportType", TransportType.BUS.name());
        Transport transport = transportService.createTransport(requestMap);
        assertNotNull(transport);
        assertEquals(MockModel.getTransport1(), transport);
    }

    @Test
    public void getTransportWithAssingUser() {
        List<Transport> transportList = transportService.getTransportWithAssingRoute();
        assertNotNull(transportList);
        assertNotNull(transportList.iterator().next().getCurrentAssignUser());
        assertEquals(Lists.newArrayList(MockModel.getTransport1()), transportList);
    }

    @Test
    public void getTransportWithAssingRoute() {
        List<Transport> transportList = transportService.getTransportWithAssingRoute();
        assertNotNull(transportList);
        assertEquals(Lists.newArrayList(MockModel.getTransport1()), transportList);
    }

    @Test
    public void assignDriver() {
        transportService.assignDriver(MockModel.idTrasport2, MockModel.getDriver().getEmail());
        assertNotNull(MockModel.getTransport2().getCurrentAssignUser());
    }
}
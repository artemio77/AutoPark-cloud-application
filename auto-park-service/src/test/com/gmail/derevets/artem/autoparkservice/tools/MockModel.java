package com.gmail.derevets.artem.autoparkservice.tools;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.User;
import com.gmail.derevets.artem.autoparkservice.model.enums.Role;
import com.gmail.derevets.artem.autoparkservice.model.enums.TransportType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TransferQueue;

@Data
public class MockModel {
    public static UUID idRouteWithTransport;
    public static UUID idRouteWithOutTransport;
    public static UUID idUser;
    public static UUID idTrasport1;
    public static UUID idTrasport2;


    private static Route routeWithTransport;
    private static Route routeWithOutTransport;
    private static User driver;
    private static Transport transport1;
    private static Transport transport2;
    private static List<Transport> transportList = Lists.newArrayList(transport1);


    public static Route getRouteWithTransport() {
        routeWithTransport = initRouteWithTransport();
        routeWithTransport.setId(UUID.randomUUID());
        idRouteWithTransport = routeWithTransport.getId();
        return routeWithTransport;
    }

    public static Route getRouteWithOutTransport() {
        routeWithOutTransport = initRouteWithOutTransport();
        routeWithOutTransport.setId(UUID.randomUUID());
        idRouteWithOutTransport = routeWithOutTransport.getId();
        return routeWithOutTransport;
    }

    public static Transport getTransport1() {
        if (transport1 == null) {
            transport1 = initTransport1();
            transport1.setId(UUID.randomUUID());
            idTrasport1 = transport1.getId();
        }
        return transport1;

    }

    public static Transport getTransport2() {
        if (transport2 == null) {
            transport2 = initTransport2();
            transport2.setId(UUID.randomUUID());
            idTrasport2 = transport2.getId();
        }
        return transport2;

    }

    public static User getDriver() {
        driver = initDriver();
        driver.setId(UUID.randomUUID());
        idUser = driver.getId();
        return driver;
    }

    private static Transport initTransport1() {
        return Transport.builder()
                .name("TEST TRANSPORT 1")
                .numberPlate("9282")
                .currentAssignUser(getDriver())
                .currentRouteAssign(getRouteWithTransport())
                .transportType(TransportType.BUS)
                .build();
    }

    private static Transport initTransport2() {
        return Transport.builder()
                .name("TEST TRANSPORT 2")
                .numberPlate("9283")
                .transportType(TransportType.BUS)
                .build();
    }

    private static Route initRouteWithOutTransport() {
        return Route.builder()
                .name("Test Route 2")
                .routeNumber(13)
                .build();

    }

    private static List<Transport> initTransportList() {
        return ImmutableList.of(getTransport1(), getTransport2());

    }

    private static User initDriver() {
        return User.builder()
                .firstName("TEST DIVER")
                .lastName("TESTDRIVER")
                .isAccountNonExpired(false)
                .isAccountNonLocked(false)
                .isCredentialsNonLocked(false)
                .isEnabled(true)
                .role(Role.ROLE_DRIVER)
                .email("test.driver@email.com")
                .build();
    }

    private static Route initRouteWithTransport() {
        final User user = getDriver();

        return Route.builder()
                .name("Test Route 1")
                .routeNumber(12)
                .users(Sets.newHashSet(user))
                .transport(Sets.newHashSet(transport1))
                .build();

    }

}

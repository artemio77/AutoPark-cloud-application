package com.gmail.derevets.artem.autoparkservice.service;

import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.User;

import java.util.List;
import java.util.UUID;

public interface TransportService {

    List<Transport> getTransportList();

    List<Transport> getTransportWithoutAssingUser();

    List<Transport> getTransportWithoutAssingRoute();

    Transport createTransport(Transport transport);

    List<Transport> getTransportWithAssingUser();

    List<Transport> getTransportWithAssingRoute();

    void assignDriver(UUID id, String email);

}

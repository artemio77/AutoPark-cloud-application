package com.gmail.derevets.artem.autoparkservice.service;

import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.enums.TransportType;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TransportService {

    List<Transport> getTransportList();

    List<Transport> getTransportWithoutAssingUser();

    List<Transport> getTransportWithoutAssingRoute();

    Transport createTransport(Map<String, String> transport);

    List<Transport> getTransportWithAssingUser();

    List<Transport> getTransportWithAssingRoute();

    Map<String, List<TransportType>> getTransportTypeList();

    void assignDriver(UUID id, String email);

}

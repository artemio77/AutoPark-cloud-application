package com.gmail.derevets.artem.autoparkservice.service.impl;

import com.gmail.derevets.artem.autoparkservice.client.UserClient;
import com.gmail.derevets.artem.autoparkservice.model.Transport;
import com.gmail.derevets.artem.autoparkservice.model.User;
import com.gmail.derevets.artem.autoparkservice.model.enums.TransportType;
import com.gmail.derevets.artem.autoparkservice.repository.TransportRepository;
import com.gmail.derevets.artem.autoparkservice.service.TransportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public List<Transport> getTransportList() {
        List<Transport> transportList = transportRepository.findAll();
        log.info("GET Transport List {}", transportList);
        return transportList;
    }

    @Override
    public List<Transport> getTransportWithoutAssingUser() {
        return transportRepository.findAllByCurrentAssignUserIsNull();
    }

    @Override
    public List<Transport> getTransportWithoutAssingRoute() {
        return transportRepository.findAllByCurrentRouteAssignIsNull();
    }

    @Override
    public Transport createTransport(Map<String, String> transportMap) {
        log.info("Transport Map {}", transportMap);
        User user = null;
        if (transportMap.containsKey("currentAssignUser")) {
            user = userClient.getUserByEmail(transportMap.get("currentAssignUser"));
        }
        log.info("Assign user , {}", user);
        Transport transport = Transport.builder()
                .name(transportMap.get("name"))
                .transportType(TransportType.valueOf(transportMap.get("transportType")))
                .currentAssignUser(user)
                .numberPlate(transportMap.get("numberPlate"))
                .build();
        log.info("CREATE Transport {}", transport);
        return transportRepository.save(transport);
    }

    @Override
    public List<Transport> getTransportWithAssingUser() {
        return transportRepository.findAllByCurrentAssignUserIsNotNull();
    }

    @Override
    public List<Transport> getTransportWithAssingRoute() {
        return transportRepository.findAllByCurrentRouteAssignIsNotNull();
    }

    @Override
    public Map<String, List<TransportType>> getTransportTypeList() {
        return Collections.singletonMap("value", Arrays.asList(TransportType.values()));
    }

    @Override
    @Transactional
    public void assignDriver(UUID id, String email) {
        User user = userClient.getUserByEmail(email);
        transportRepository.assignDriverOnTransport(user.getId(), id);

    }

}

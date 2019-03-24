package com.gmail.derevets.artem.autoparkservice.repository;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RouteRepository extends CrudRepository<Route, UUID> {
    List<Route> findByTransportOnRouteListIsNull();

}

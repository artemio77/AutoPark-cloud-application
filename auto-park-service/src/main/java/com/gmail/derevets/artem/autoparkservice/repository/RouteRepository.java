package com.gmail.derevets.artem.autoparkservice.repository;

import com.gmail.derevets.artem.autoparkservice.model.Route;
import com.gmail.derevets.artem.autoparkservice.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RouteRepository extends CrudRepository<Route, UUID> {

    Optional<Route> findByUsersIn(User user);

    List<Route> findAllByTransportIsNull();

    List<Route> findAllByTransportIsNotNull();

    List<Route> findAll();

    List<Route> findAllByIdIn(List<UUID> id);


}

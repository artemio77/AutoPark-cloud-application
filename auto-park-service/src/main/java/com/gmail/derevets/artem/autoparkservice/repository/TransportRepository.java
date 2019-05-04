package com.gmail.derevets.artem.autoparkservice.repository;

import com.gmail.derevets.artem.autoparkservice.model.Transport;
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
public interface TransportRepository extends CrudRepository<Transport, UUID> {

    Optional<Transport> findByCurrentAssignUser(User user);

    List<Transport> findAll();

    List<Transport> findAllByCurrentRouteAssignIsNull();

    List<Transport> findAllByCurrentAssignUserIsNull();

    List<Transport> findAllByCurrentAssignUserIsNotNull();

    List<Transport> findAllByCurrentRouteAssignIsNotNull();

    @Modifying
    @Query(value = "update transport set user_id= :userId where id= :transportId", nativeQuery = true)
    void assignDriverOnTransport(@Param("userId") UUID userId, @Param("transportId") UUID transportId);

    @Modifying
    @Query(value = "update transport set route_id= :routeId where id= :transportId", nativeQuery = true)
    void assignRouteOnTransport(@Param("routeId") UUID routeId, @Param("transportId") UUID transportId);

    @Modifying
    @Query(value = "update transport set route_id= null where id= :transportId", nativeQuery = true)
    void removeAssignRouteOnTransport(@Param("transportId") UUID transportId);
}

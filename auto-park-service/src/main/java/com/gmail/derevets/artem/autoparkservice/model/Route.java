package com.gmail.derevets.artem.autoparkservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity(name = "route")
public class Route extends BaseEntity<UUID> {


    private String name;

    private String description;

    @Column(name = "route_number")
    private Integer routeNumber;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "currentRouteAssign", cascade = CascadeType.ALL)
    private Set<Transport> transport;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private Set<User> users;

}

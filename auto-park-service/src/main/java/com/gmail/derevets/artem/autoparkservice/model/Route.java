package com.gmail.derevets.artem.autoparkservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "route")
public class Route extends BaseEntity<UUID> {


    private String name;

    private String description;

    @Column(name = "route_number")
    private Integer routeNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "currentRouteAssign", cascade = CascadeType.ALL)
    private List<Transport> transport;

}

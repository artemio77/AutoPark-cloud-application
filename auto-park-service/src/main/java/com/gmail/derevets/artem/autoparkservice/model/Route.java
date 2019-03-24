package com.gmail.derevets.artem.autoparkservice.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "route")
@EqualsAndHashCode(exclude = "transportOnRouteList", callSuper = false)
public class Route extends BaseEntity<UUID> {


    private String name;

    private String description;

    private String raceNumber;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Transport> transportOnRouteList;

}

package com.gmail.derevets.artem.autoparkservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "route")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Route extends BaseEntity<UUID> {


    private String name;

    private String description;

    @Column(name = "route_number")
    private Integer routeNumber;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference(value = "transport-route")
    @OneToMany(mappedBy = "currentRouteAssign", cascade = CascadeType.ALL)
    private Set<Transport> transport;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference(value = "route-user")
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private Set<User> users;

}

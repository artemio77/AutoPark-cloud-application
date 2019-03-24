package com.gmail.derevets.artem.autoparkservice.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.gmail.derevets.artem.autoparkservice.model.enums.TransportType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Entity(name = "transport")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transport extends BaseEntity<UUID> {


    private String name;

    private TransportType transportType;

    private String numberPlate;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route currentRouteAssign;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User currentAssignUser;


}

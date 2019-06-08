package com.gmail.derevets.artem.autoparkservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gmail.derevets.artem.autoparkservice.model.enums.TransportType;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transport")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transport extends BaseEntity<UUID> {


    private String name;

    private TransportType transportType;

    private String numberPlate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "transport-route")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route currentRouteAssign;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JsonBackReference("transport-user")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User currentAssignUser;


}

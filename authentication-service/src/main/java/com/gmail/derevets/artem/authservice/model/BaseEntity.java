package com.gmail.derevets.artem.authservice.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.uuid.Generators;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author Artem Derevets
 */
@Data
@MappedSuperclass
public abstract class BaseEntity<ID> {

    @Id
    @JsonProperty("id")
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime creationTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime modificationTime;

    @Version
    private long version;


    @PrePersist
    public void prePersist() {
        this.id = Generators.timeBasedGenerator().generate();
        ZonedDateTime now = ZonedDateTime.now();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = ZonedDateTime.now();
    }
}



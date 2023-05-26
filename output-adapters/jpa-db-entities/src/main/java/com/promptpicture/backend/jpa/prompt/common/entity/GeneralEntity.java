package com.promptpicture.backend.jpa.prompt.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class GeneralEntity implements Serializable {

    public static final String ZONE_NAME = "Europe/Prague";
    public static final ZoneId ZONE_ID = ZoneId.of(ZONE_NAME);

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


    @PrePersist
    public void onInsert() {

        final OffsetDateTime sysdate = promptCurrentTime();
        setCreatedAt(sysdate);
        setUpdatedAt(sysdate);
    }

    @PreUpdate
    public void onUpdate() {

        setUpdatedAt(promptCurrentTime());
    }

    protected OffsetDateTime promptCurrentTime() {

        return ZonedDateTime.now(ZONE_ID).toOffsetDateTime();
    }

}

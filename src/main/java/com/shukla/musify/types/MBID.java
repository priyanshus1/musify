package com.shukla.musify.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class MBID {

    @NotNull
    @Valid
    @JsonValue
    private UUID uuid;


    @JsonCreator
    public MBID(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
    
    @Override
    public String toString() {
        return this.uuid.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MBID mbid = (MBID) obj;
        return Objects.equals(this.uuid, mbid.uuid);
    }
}




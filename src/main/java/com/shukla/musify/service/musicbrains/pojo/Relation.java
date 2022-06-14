package com.shukla.musify.service.musicbrains.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.UUID;

public class Relation {

    @JsonProperty("type-id")
    private UUID typeId;
    private String type;
    private Map<String, String> url;

    private Relation() {
    }

    public String getType() {
        return this.type;
    }

    public Map<String, String> getUrl() {
        return this.url;
    }

    public UUID getTypeId() {
        return this.typeId;
    }
}

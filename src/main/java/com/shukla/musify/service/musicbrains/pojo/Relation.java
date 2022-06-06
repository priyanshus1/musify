package com.shukla.musify.service.musicbrains.pojo;

import java.util.Map;

public class Relation {
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
}

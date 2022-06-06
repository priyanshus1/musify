package com.shukla.musify.service.musicbrains.pojo;

import java.util.UUID;

public class ReleaseGroup {
    private UUID id;
    private String title;

    private ReleaseGroup() {
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}

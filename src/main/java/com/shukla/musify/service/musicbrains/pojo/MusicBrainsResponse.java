package com.shukla.musify.service.musicbrains.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shukla.musify.service.pojo.Gender;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class MusicBrainsResponse {
    private UUID id;
    private String name;
    private String country;

    private Gender gender;

    private String disambiguation;
    @JsonProperty("release-groups")
    private List<ReleaseGroup> releaseGroups = emptyList();

    private List<Relation> relations = emptyList();

    private MusicBrainsResponse() {
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

    public Gender getGender() {
        return this.gender;
    }

    public List<ReleaseGroup> getReleaseGroups() {
        return unmodifiableList(this.releaseGroups);
    }


    public List<Relation> getRelations() {
        return unmodifiableList(this.relations);
    }

    public String getDisambiguation() {
        return this.disambiguation;
    }
}


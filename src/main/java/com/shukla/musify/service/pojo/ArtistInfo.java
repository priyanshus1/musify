package com.shukla.musify.service.pojo;


import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.unmodifiableList;

public class ArtistInfo {

    private UUID mbid;
    private String name;
    private Gender gender;
    private String country;
    private String disambiguation;
    private String description;
    private List<Album> albums;

    public ArtistInfo() {
    }

    private ArtistInfo(UUID mbid, String name, Gender gender, String country, String disambiguation, String description, List<Album> albums) {
        this.mbid = mbid;
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.disambiguation = disambiguation;
        this.description = description;
        this.albums = albums;
    }

    public UUID getMbid() {
        return this.mbid;
    }

    public String getName() {
        return this.name;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getCountry() {
        return this.country;
    }

    public String getDisambiguation() {
        return this.disambiguation;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Album> getAlbums() {
        return unmodifiableList(this.albums);
    }

    public static ArtistInfoBuilder builder() {
        return new ArtistInfoBuilder();
    }

    public static class ArtistInfoBuilder {
        private UUID mbid;
        private String name;
        private Gender gender;
        private String country;
        private String disambiguation;
        private String description;
        private List<Album> albums = Collections.emptyList();

        public ArtistInfoBuilder mbid(UUID mbid) {
            this.mbid = mbid;
            return this;
        }

        public ArtistInfoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ArtistInfoBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public ArtistInfoBuilder country(String country) {
            this.country = country;
            return this;
        }

        public ArtistInfoBuilder disambiguation(String disambiguation) {
            this.disambiguation = disambiguation;
            return this;
        }

        public ArtistInfoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ArtistInfoBuilder albums(List<Album> albums) {
            this.albums = albums;
            return this;
        }

        public ArtistInfo build() {
            return new ArtistInfo(this.mbid, this.name, this.gender, this.country, this.disambiguation, this.description, this.albums);
        }

    }

}

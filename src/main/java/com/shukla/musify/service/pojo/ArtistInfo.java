package com.shukla.musify.service.pojo;


import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.unmodifiableList;

/*
{
"mbid": "f27ec8db-af05-4f36-916e-3d57f91ecf5e",
"name": "Michael Jackson",
"gender": "Male",
"country": "US",
"disambiguation": "“King of Pop”",
"description": "<p><b>Michael Joseph Jackson</b> (August 29, 1958 –
June 25, 2009) was an American singer, songwriter, record producer,
dancer, and actor. Called the King of Pop, his contributions to music,
dance, and fashion along with his publicized personal life made him a
global figure in popular culture for over four decades.</p>\n<p>The
eighth ............. with $115 million in earnings.</p>\n<p></p>",
"albums": [
{
"id": "500d9b05-68c3-3535-86e3-cf685869efc0",
"title": "Farewell My Summer Love",
"imageUrl": "http://coverartarchive.org/release/8172928a-a6c7-
4d7c-83c8-5db2a4575094/13404444760.jpg"
 */
public class ArtistInfo {

    private UUID mbid;
    private String name;
    private Gender gender;
    private String country;
    private String disambiguation;
    private String description;
    private List<Album> albums;

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

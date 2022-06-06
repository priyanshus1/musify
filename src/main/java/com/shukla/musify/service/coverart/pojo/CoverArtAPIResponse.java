package com.shukla.musify.service.coverart.pojo;


import java.util.List;

public class CoverArtAPIResponse {

    private List<CoverArtImage> images;

    private CoverArtAPIResponse() {
    }

    public List<CoverArtImage> getImages() {
        return this.images;
    }
}

package com.shukla.musify.controller;

import com.shukla.musify.service.ArtistInfoProviderService;
import com.shukla.musify.service.pojo.ArtistInfo;
import com.shukla.musify.service.wiki.exception.MusicBrainInvalidWikiUrlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(path = "/musify/music-artist", produces = APPLICATION_JSON_VALUE)
public class ArtistRestController {

    private final ArtistInfoProviderService artistInfoProviderService;

    @Autowired
    public ArtistRestController(ArtistInfoProviderService artistInfoProviderService) {
        this.artistInfoProviderService = artistInfoProviderService;
    }

    @GetMapping("/details/{mbid}")
    public ArtistInfo getArtist(@PathVariable @NotNull @Valid UUID mbid) throws MusicBrainInvalidWikiUrlException {
        return this.artistInfoProviderService.getArtistInfo(mbid);
    }
}

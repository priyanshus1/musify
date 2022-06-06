package com.shukla.musify.service;

import com.shukla.musify.service.exception.MusicBrainWikiRelationNotFoundException;
import com.shukla.musify.service.musicbrains.MusicBrainsAPIService;
import com.shukla.musify.service.musicbrains.pojo.MusicBrainsResponse;
import com.shukla.musify.service.musicbrains.pojo.Relation;
import com.shukla.musify.service.pojo.Album;
import com.shukla.musify.service.pojo.ArtistInfo;
import com.shukla.musify.service.wiki.WikiDataAPIService;
import com.shukla.musify.service.wiki.exception.MusicBrainInvalidWikiUrlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ArtistInfoProviderService {

    private final MusicBrainsAPIService musicBrainsAPIService;
    private final WikiDataAPIService wikiDataAPIService;

    @Autowired
    public ArtistInfoProviderService(MusicBrainsAPIService musicBrainsAPIService, WikiDataAPIService wikiDataAPIService) {
        this.musicBrainsAPIService = musicBrainsAPIService;
        this.wikiDataAPIService = wikiDataAPIService;
    }

    public ArtistInfo getArtistInfo(@Valid @NotNull UUID mbid) throws MusicBrainInvalidWikiUrlException {
        MusicBrainsResponse musicBrainsResponse = this.musicBrainsAPIService.fetchArtistInfo(mbid);
        List<Album> mappedAlbum = musicBrainsResponse.getReleaseGroups().stream()
                .map(e -> new Album(e.getId(), e.getTitle(), null)).toList();

        Relation wikidata = musicBrainsResponse
                .getRelations()
                .stream()
                .filter(e -> Objects.equals(e.getType(), "wikidata"))
                .findFirst()
                .orElseThrow(MusicBrainWikiRelationNotFoundException::new);

        this.wikiDataAPIService.fetchWikiData(wikidata);
        return ArtistInfo.builder()
                .mbid(mbid)
                .name(musicBrainsResponse.getName())
                .country(musicBrainsResponse.getCountry())
                .gender(musicBrainsResponse.getGender())
                .disambiguation(musicBrainsResponse.getDisambiguation())
                .albums(mappedAlbum)
                .build();
    }

}

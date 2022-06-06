package com.shukla.musify.service;

import com.shukla.musify.service.coverart.CoverArtAPIService;
import com.shukla.musify.service.exception.MusicBrainWikiRelationNotFoundException;
import com.shukla.musify.service.musicbrains.MusicBrainsAPIService;
import com.shukla.musify.service.musicbrains.pojo.MusicBrainsResponse;
import com.shukla.musify.service.musicbrains.pojo.Relation;
import com.shukla.musify.service.musicbrains.pojo.ReleaseGroup;
import com.shukla.musify.service.pojo.Album;
import com.shukla.musify.service.pojo.ArtistInfo;
import com.shukla.musify.service.wiki.WikiAPIService;
import com.shukla.musify.service.wiki.exception.MusicBrainInvalidWikiUrlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class ArtistInfoProviderService {

    private final MusicBrainsAPIService musicBrainsAPIService;
    private final WikiAPIService wikiAPIService;
    private final CoverArtAPIService coverArtAPIService;

    @Autowired
    public ArtistInfoProviderService(MusicBrainsAPIService musicBrainsAPIService, WikiAPIService wikiAPIService, CoverArtAPIService coverArtAPIService) {
        this.musicBrainsAPIService = musicBrainsAPIService;
        this.wikiAPIService = wikiAPIService;
        this.coverArtAPIService = coverArtAPIService;
    }

    public ArtistInfo getArtistInfo(@Valid @NotNull UUID mbid) throws MusicBrainInvalidWikiUrlException {
        MusicBrainsResponse musicBrainsResponse = this.musicBrainsAPIService.fetchArtistInfo(mbid);
        Relation wikidata = musicBrainsResponse
                .getRelations()
                .stream()
                .filter(e -> Objects.equals(e.getType(), "wikidata"))
                .findFirst()
                .orElseThrow(MusicBrainWikiRelationNotFoundException::new);

        String description = this.wikiAPIService.getDescription(wikidata);

        List<ReleaseGroup> releaseGroups = musicBrainsResponse.getReleaseGroups();
        Map<UUID, String> imageUrlForMbid = this.coverArtAPIService.fetchCoverArtForReleaseGroups(releaseGroups);
        List<Album> mappedAlbum = releaseGroups.stream()
                .map(e -> new Album(e.getId(), e.getTitle(), imageUrlForMbid.get(e.getId()))).toList();
        return ArtistInfo.builder()
                .mbid(mbid)
                .name(musicBrainsResponse.getName())
                .country(musicBrainsResponse.getCountry())
                .gender(musicBrainsResponse.getGender())
                .disambiguation(musicBrainsResponse.getDisambiguation())
                .albums(mappedAlbum)
                .description(description)
                .build();
    }

}

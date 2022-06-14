package com.shukla.musify.service;

import com.shukla.musify.service.coverart.CoverArtAPIService;
import com.shukla.musify.service.musicbrains.MusicBrainsAPIService;
import com.shukla.musify.service.musicbrains.exception.MusicBrainInvalidWikiUrlException;
import com.shukla.musify.service.musicbrains.exception.MusicBrainWikiRelationNotFoundException;
import com.shukla.musify.service.musicbrains.pojo.MusicBrainsResponse;
import com.shukla.musify.service.musicbrains.pojo.Relation;
import com.shukla.musify.service.musicbrains.pojo.ReleaseGroup;
import com.shukla.musify.service.pojo.Album;
import com.shukla.musify.service.pojo.ArtistInfo;
import com.shukla.musify.service.wiki.WikiAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    public ArtistInfo getArtistInfo(@Valid @NotNull UUID mbid) throws MusicBrainInvalidWikiUrlException, ExecutionException, InterruptedException {
        MusicBrainsResponse musicBrainsResponse = this.musicBrainsAPIService.fetchArtistInfo(mbid);
        Relation wikidata = musicBrainsResponse
                .getRelations()
                .stream()
                .filter(e -> Objects.equals(e.getType(), "wikidata"))
                .findFirst()
                .orElseThrow(MusicBrainWikiRelationNotFoundException::new);

        Future<String> description = this.wikiAPIService.getDescriptionAsync(wikidata);
        List<Album> mappedAlbum = getAlbumsForReleaseGroups(musicBrainsResponse.getReleaseGroups());

        return ArtistInfo.builder()
                .mbid(mbid)
                .name(musicBrainsResponse.getName())
                .country(musicBrainsResponse.getCountry())
                .gender(musicBrainsResponse.getGender())
                .disambiguation(musicBrainsResponse.getDisambiguation())
                .albums(mappedAlbum)
                .description(description.get())
                .build();
    }

    private List<Album> getAlbumsForReleaseGroups(List<ReleaseGroup> releaseGroups) {
        return releaseGroups.stream()
                .map(e -> {
                            try {
                                return new Album(e.getId(), e.getTitle(), this.coverArtAPIService.fetchCoverArtASync(e.getId()));
                            } catch (InterruptedException | ExecutionException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                ).toList();
    }

}

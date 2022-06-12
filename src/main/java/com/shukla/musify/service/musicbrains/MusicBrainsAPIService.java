package com.shukla.musify.service.musicbrains;

import com.shukla.musify.base.AMusifyRestTemplate;
import com.shukla.musify.service.musicbrains.pojo.MusicBrainsResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
public class MusicBrainsAPIService extends AMusifyRestTemplate<MusicBrainsResponse> {

    private static final String API_URL = "http://musicbrainz.org/ws/2/artist/";
    private static final String URL_PARAM = "?&fmt=json&inc=url-rels+release-groups";
    public static final String MUSIC_BRAINZ = "MusicBrainz";

    public MusicBrainsAPIService() {
        super();
    }

    @Override
    protected String getServiceName() {
        return MUSIC_BRAINZ;
    }

    @Cacheable(value = "musicBrainResponses")
    public MusicBrainsResponse fetchArtistInfo(@NotNull UUID mbid) {
        String fetchQuery = API_URL + mbid + URL_PARAM;
        return this.getForEntity(fetchQuery, MusicBrainsResponse.class);
    }
}

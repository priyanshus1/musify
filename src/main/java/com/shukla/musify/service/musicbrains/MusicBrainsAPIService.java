package com.shukla.musify.service.musicbrains;

import com.shukla.musify.base.AMusifyRestTemplate;
import com.shukla.musify.service.musicbrains.exception.MusicBrainsAPIUnexpectedStatusCodeException;
import com.shukla.musify.service.musicbrains.pojo.MusicBrainsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.shukla.musify.error.MusifyErrorCode.MUSIC_BRAINS_NO_RESPONSE;
import static com.shukla.musify.error.MusifyErrorCode.MUSIC_BRAINS_UNEXPECTED_STATUS_CODE;

@Service
public class MusicBrainsAPIService extends AMusifyRestTemplate {

    private static final String API_URL = "http://musicbrainz.org/ws/2/artist/";
    private static final String URL_PARAM = "?&fmt=json&inc=url-rels+release-groups";

    public MusicBrainsAPIService() {
        super();
    }

    public MusicBrainsResponse fetchArtistInfo(@NotNull UUID mbid) {
        String fetchQuery = API_URL + mbid + URL_PARAM;
        ResponseEntity<MusicBrainsResponse> response = this.restTemplate.getForEntity(fetchQuery, MusicBrainsResponse.class);
        return validateResponseOrThrow(response);
    }

    private MusicBrainsResponse validateResponseOrThrow(ResponseEntity<MusicBrainsResponse> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new MusicBrainsAPIUnexpectedStatusCodeException(MUSIC_BRAINS_UNEXPECTED_STATUS_CODE, response.getStatusCode());
        }

        MusicBrainsResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new MusicBrainsAPINoResponseException(MUSIC_BRAINS_NO_RESPONSE);
        }

        return responseBody;
    }
}

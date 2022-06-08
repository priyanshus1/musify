package com.shukla.musify.base;

import com.shukla.musify.service.musicbrains.MusicBrainsAPINoResponseException;
import com.shukla.musify.service.musicbrains.exception.MusicBrainsAPIUnexpectedStatusCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.shukla.musify.base.error.MusifyErrorCode.MUSIC_BRAINS_NO_RESPONSE;
import static com.shukla.musify.base.error.MusifyErrorCode.MUSIC_BRAINS_UNEXPECTED_STATUS_CODE;

public class AMusifyRestTemplate<T> {
    private final RestTemplate restTemplate;

    protected AMusifyRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    protected T getForEntity(String url, Class<T> responseClass) {
        ResponseEntity<T> response = this.restTemplate.getForEntity(url, responseClass);
        return this.validateResponseOrThrow(response);
    }

    private T validateResponseOrThrow(ResponseEntity<T> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new MusicBrainsAPIUnexpectedStatusCodeException(MUSIC_BRAINS_UNEXPECTED_STATUS_CODE, response.getStatusCode());
        }

        T responseBody = response.getBody();
        if (responseBody == null) {
            throw new MusicBrainsAPINoResponseException(MUSIC_BRAINS_NO_RESPONSE);
        }
        return responseBody;
    }
}

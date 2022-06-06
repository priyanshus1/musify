package com.shukla.musify.base;

import com.google.common.reflect.TypeParameter;
import com.shukla.musify.service.musicbrains.MusicBrainsAPINoResponseException;
import com.shukla.musify.service.musicbrains.exception.MusicBrainsAPIUnexpectedStatusCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.shukla.musify.error.MusifyErrorCode.MUSIC_BRAINS_NO_RESPONSE;
import static com.shukla.musify.error.MusifyErrorCode.MUSIC_BRAINS_UNEXPECTED_STATUS_CODE;

public class AMusifyRestTemplate<T> {
    protected final RestTemplate restTemplate;

    public AMusifyRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    public T getForEntity(String url) {
        this.restTemplate.getForEntity(url, new TypeParameter<T>() {
        });
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

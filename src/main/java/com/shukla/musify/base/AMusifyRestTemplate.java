package com.shukla.musify.base;

import com.shukla.musify.base.exception.MusifyNoResponseException;
import com.shukla.musify.base.exception.MusifyServiceCommunicationException;
import com.shukla.musify.base.exception.MusifyUnexpectedHttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public abstract class AMusifyRestTemplate<T> {
    private final RestTemplate restTemplate;

    protected AMusifyRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    protected T getForEntity(String url, Class<T> responseClass) {
        try {
            ResponseEntity<T> response = this.restTemplate.getForEntity(url, responseClass);
            return this.validateResponseOrThrow(response);
        } catch (HttpClientErrorException e) {
            throw new MusifyServiceCommunicationException(this.getServiceName(), e);
        }
    }

    private T validateResponseOrThrow(ResponseEntity<T> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new MusifyUnexpectedHttpStatusException(response.getStatusCode(), this.getServiceName());
        }

        T responseBody = response.getBody();
        if (responseBody == null) {
            throw new MusifyNoResponseException(this.getServiceName());
        }
        return responseBody;
    }

    protected abstract String getServiceName();
}

package com.shukla.musify.base.exception;

import com.shukla.musify.base.error.AMusifyBaseException;

import static com.shukla.musify.base.error.MusifyErrorCode.NO_RESPONSE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class MusifyNoResponseException extends AMusifyBaseException {
    public MusifyNoResponseException(String serviceName) {
        super(NO_RESPONSE, NOT_FOUND, serviceName);
    }
}

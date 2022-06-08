package com.shukla.musify.base.error;

import org.springframework.http.HttpStatus;

public abstract class AMusifyBaseException extends RuntimeException {

    public AMusifyBaseException(MusifyErrorCode errorCode, HttpStatus httpStatus, String... placeHolders) {
        super();
    }


}

package com.shukla.musify.base.exception;

import com.shukla.musify.base.error.AMusifyBaseException;
import com.shukla.musify.base.error.MusifyErrorCode;
import org.springframework.http.HttpStatus;

public class MusifyUnexpectedHttpStatusException extends AMusifyBaseException {
    public MusifyUnexpectedHttpStatusException(HttpStatus statusCode, String serviceName) {
        super(MusifyErrorCode.UNEXPECTED_STATUS_CODE, statusCode, serviceName, statusCode.toString());
    }
}

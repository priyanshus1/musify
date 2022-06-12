package com.shukla.musify.base.error;

import org.springframework.http.HttpStatus;

public abstract class AMusifyBaseException extends RuntimeException {

    protected final ErrorData errorData;

    public AMusifyBaseException(MusifyErrorCode errorCode, HttpStatus httpStatus, String... placeHolders) {
        this.errorData = new ErrorData(errorCode, httpStatus, placeHolders);

    }

    public AMusifyBaseException(MusifyErrorCode errorCode, HttpStatus httpStatus, Throwable e) {
        this.errorData = new ErrorData(errorCode, httpStatus);
        initCause(e);
    }

    public ErrorData getErrorData() {
        return this.errorData;
    }


}

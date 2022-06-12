package com.shukla.musify.base.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.UUID;

import static java.util.Locale.ENGLISH;

public class ErrorData {
    private final UUID errorUuid;
    private final MusifyErrorCode errorCode;
    private final String errorLogMessage;
    private final HttpStatus httpStatus;

    ErrorData(MusifyErrorCode errorCode, HttpStatus httpStatus, String... placeHolders) {
        this.errorUuid = UUID.randomUUID();
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorLogMessage = this.generateErrorMessageForLog(placeHolders);

    }

    private String generateErrorMessageForLog(String... placeHolders) {
        MessageFormat messageFormat = new MessageFormat(this.errorCode.getDescription(), ENGLISH);
        return messageFormat.format(placeHolders);
    }

    public UUID getErrorUuid() {
        return this.errorUuid;
    }

    public String getErrorCode() {
        return "[" + this.errorCode.getPrefix() + this.errorCode.getErrorCode() + "]";
    }

    public String getMessage() {
        return this.errorCode.getExternalMessage();
    }

    @JsonIgnore
    public String getErrorLogMessage() {
        return this.errorLogMessage;
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}

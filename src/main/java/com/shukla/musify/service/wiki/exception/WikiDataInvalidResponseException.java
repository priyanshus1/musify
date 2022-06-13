package com.shukla.musify.service.wiki.exception;

import com.shukla.musify.base.error.AMusifyBaseException;
import com.shukla.musify.base.error.MusifyErrorCode;
import org.springframework.http.HttpStatus;

public class WikiDataInvalidResponseException extends AMusifyBaseException {
    public WikiDataInvalidResponseException(String url) {
        super(MusifyErrorCode.INVALID_RESPONSE_FROM_WIKI_DATA, HttpStatus.UNPROCESSABLE_ENTITY, url);
    }
}

package com.shukla.musify.service.wiki.exception;

import com.shukla.musify.base.error.AMusifyBaseException;
import com.shukla.musify.base.error.MusifyErrorCode;
import org.springframework.http.HttpStatus;

public class WikiDataMissingSiteLinkException extends AMusifyBaseException {
    public WikiDataMissingSiteLinkException() {
        super(MusifyErrorCode.WIKI_DATA_RESPONSE_MISSING_SITELINK, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

package com.shukla.musify.service.wiki.exception;

import com.shukla.musify.base.error.AMusifyBaseException;
import com.shukla.musify.base.error.MusifyErrorCode;
import org.springframework.http.HttpStatus;

public class WikiDataMissingWikiUrlException extends AMusifyBaseException {
    public WikiDataMissingWikiUrlException() {
        super(MusifyErrorCode.MISSING_WIKIPEDIA_URL_IN_WIKI_DATA_RESPONSE, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

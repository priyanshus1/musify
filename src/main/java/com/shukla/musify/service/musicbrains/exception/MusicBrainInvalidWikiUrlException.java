package com.shukla.musify.service.musicbrains.exception;

import com.shukla.musify.base.error.AMusifyBaseException;
import com.shukla.musify.base.error.MusifyErrorCode;
import org.springframework.http.HttpStatus;

public class MusicBrainInvalidWikiUrlException extends AMusifyBaseException {
    public MusicBrainInvalidWikiUrlException(String resourceUrl) {
        super(MusifyErrorCode.INVALID_WIKI_URL_IN_RELATION_MUSIC_BRAIN, HttpStatus.UNPROCESSABLE_ENTITY, resourceUrl);
    }
}

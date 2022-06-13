package com.shukla.musify.service.musicbrains.exception;

import com.shukla.musify.base.error.AMusifyBaseException;

import static com.shukla.musify.base.error.MusifyErrorCode.MISSING_WIKI_RELATION_IN_MUSIC_BRAINS_RESPONSE;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class MusicBrainWikiRelationNotFoundException extends AMusifyBaseException {
    public MusicBrainWikiRelationNotFoundException() {
        super(MISSING_WIKI_RELATION_IN_MUSIC_BRAINS_RESPONSE, UNPROCESSABLE_ENTITY);
    }
}

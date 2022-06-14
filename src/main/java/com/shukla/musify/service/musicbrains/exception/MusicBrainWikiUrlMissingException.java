package com.shukla.musify.service.musicbrains.exception;

import com.shukla.musify.base.error.AMusifyBaseException;
import com.shukla.musify.base.error.MusifyErrorCode;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class MusicBrainWikiUrlMissingException extends AMusifyBaseException {
    public MusicBrainWikiUrlMissingException() {
        super(MusifyErrorCode.MISSING_WIKI_URL_RELATION_MUSIC_BRAIN, UNPROCESSABLE_ENTITY);
    }
}

package com.shukla.musify.service.musicbrains.exception;

import com.shukla.musify.base.error.MusifyErrorCode;
import org.springframework.http.HttpStatus;

public class MusicBrainsAPIUnexpectedStatusCodeException extends RuntimeException {
    public MusicBrainsAPIUnexpectedStatusCodeException(MusifyErrorCode musicBrainsUnexpectedStatusCode, HttpStatus statusCode) {
    }
}

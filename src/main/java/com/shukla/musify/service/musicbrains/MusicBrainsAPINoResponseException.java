package com.shukla.musify.service.musicbrains;

import com.shukla.musify.base.error.MusifyErrorCode;

public class MusicBrainsAPINoResponseException extends RuntimeException {
    public MusicBrainsAPINoResponseException(MusifyErrorCode musicBrainsNoResponse) {
    }
}

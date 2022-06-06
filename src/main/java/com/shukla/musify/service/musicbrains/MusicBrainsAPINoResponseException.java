package com.shukla.musify.service.musicbrains;

import com.shukla.musify.error.MusifyErrorCode;

public class MusicBrainsAPINoResponseException extends RuntimeException {
    public MusicBrainsAPINoResponseException(MusifyErrorCode musicBrainsNoResponse) {
    }
}

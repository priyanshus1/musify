package com.shukla.musify.base.error;

public enum MusifyErrorCode {
    MUSIC_BRAINS_UNEXPECTED_STATUS_CODE(001, "Musicbrains API returned unexpected http status code {0}", "Internal Server Error"),
    MUSIC_BRAINS_NO_RESPONSE(002, "Musicbrians API returned empty response", "Internal Server Error");
    private final int errorCode;
    private final String description;
    private final String externalMessage;

    MusifyErrorCode(int errorCode, String description, String externalMessage) {
        this.errorCode = errorCode;
        this.description = description;
        this.externalMessage = externalMessage;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getDescription() {
        return this.description;
    }

    public String getExternalMessage() {
        return this.externalMessage;
    }
}

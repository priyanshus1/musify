package com.shukla.musify.base.error;

public enum MusifyErrorCode {
    INTERNAL_SERVER_ERROR(000, "Failed to process request due to:", "Internal Server Error"),
    UNEXPECTED_STATUS_CODE(001, "{0} API returned unexpected http status code {1}", "Failed to retrieve data for this query"),
    NO_RESPONSE(002, "{0} API returned empty response", "Failed to retrieve data for this query"),
    MISSING_WIKI_RELATION_IN_MUSIC_BRAINS_RESPONSE(100, "Wiki relation missing in Music Brain Response", "Cannot process this request"),
    MISSING_WIKI_URL_RELATION_MUSIC_BRAIN(101, "Wiki relation in Music Brain Response does not contain a url", "Cannot process this request"),
    INVALID_WIKI_URL_IN_RELATION_MUSIC_BRAIN(102, "Wiki relation in Music Brain Response contains an invalid url: {0}", "Cannot process this request"),
    INVALID_RESPONSE_FROM_WIKI_DATA(201, "Wiki data returned an invalid response for the resource: {0}", "Cannot process this request"),
    MISSING_WIKIPEDIA_URL_IN_WIKI_DATA_RESPONSE(202, "Wikipedia url missing from wiki data response", "Cannot process this request"),
    WIKI_DATA_RESPONSE_MISSING_SITELINK(203, "Wiki data response does not contain site link entity", "Cannot process this request");
    private final int errorCode;
    private final String description;
    private final String externalMessage;

    private static final String prefix = "MusifyErrorCode -";

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

    public String getPrefix() {
        return MusifyErrorCode.prefix;
    }
}

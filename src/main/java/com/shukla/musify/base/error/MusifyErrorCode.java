package com.shukla.musify.base.error;

public enum MusifyErrorCode {
    INTERNAL_SERVER_ERROR(000, "Failed to process request due to:", "Internal Server Error"),
    UNEXPECTED_STATUS_CODE(001, "{0} API returned unexpected http status code {1}", "Failed to retrieve data for this query"),
    NO_RESPONSE(002, "{0} API returned empty response", "Failed to retrieve data for this query"),
    ;
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

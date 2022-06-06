package com.shukla.musify.service.wiki.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WikipediaResponse {
    
    @JsonProperty("extract_html")
    private String extractHtml;

    private String description;

    private WikipediaResponse() {
    }

    public String getExtractHtml() {
        return this.extractHtml;
    }

    public String getDescription() {
        return this.description;
    }
}

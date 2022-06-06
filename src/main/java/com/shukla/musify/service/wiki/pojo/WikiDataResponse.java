package com.shukla.musify.service.wiki.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.Map;

import static java.util.Collections.emptyMap;

public class WikiDataResponse {

    @JsonProperty("sitelinks")
    private final Map<String, WikiSite> siteLinks = emptyMap();

    private WikiDataResponse() {
    }

    public Map<String, WikiSite> getSiteLinks() {
        return Collections.unmodifiableMap(this.siteLinks);
    }
}

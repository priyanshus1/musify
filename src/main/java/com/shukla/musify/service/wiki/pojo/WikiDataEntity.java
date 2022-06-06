package com.shukla.musify.service.wiki.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

import static java.util.Collections.emptyMap;

public class WikiDataEntity {

    @JsonProperty("sitelinks")
    private final Map<String, WikiSite> siteLinks = emptyMap();

    private WikiDataEntity() {
    }

    public Map<String, WikiSite> getSiteLinks() {
        return this.siteLinks;
    }
}

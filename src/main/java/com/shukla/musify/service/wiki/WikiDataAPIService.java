package com.shukla.musify.service.wiki;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shukla.musify.base.AMusifyRestTemplate;
import com.shukla.musify.service.musicbrains.exception.MusicBrainInvalidWikiUrlException;
import com.shukla.musify.service.musicbrains.pojo.Relation;
import com.shukla.musify.service.wiki.exception.WikiDataInvalidResponseException;
import com.shukla.musify.service.wiki.exception.WikiDataMissingWikiUrlException;
import com.shukla.musify.service.wiki.pojo.WikiDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WikiDataAPIService extends AMusifyRestTemplate<JsonNode> {

    private static final String VALIDATION_MATCH = "https://www\\.wikidata\\.org/wiki/[A-Za-z0-9_-]*$";
    private static final Pattern URL_VALIDATION_PATTERN = Pattern.compile(VALIDATION_MATCH);
    private static final String WIKI_DATA_API_URL = "https://www.wikidata.org/wiki/Special:EntityData/";
    public static final String WIKI_DATA = "WikiData";

    private final ObjectMapper objectMapper;

    @Autowired
    public WikiDataAPIService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public WikiDataEntity fetchWikiData(Relation wikiData) throws MusicBrainInvalidWikiUrlException {
        String resourceUrl = wikiData.getUrl().get("resource");
        String resourceId = extractResourceIdOrThrow(resourceUrl);
        String fetchQuery = WIKI_DATA_API_URL + resourceId + ".json";
        JsonNode response = this.getForEntity(fetchQuery, JsonNode.class);
        return extractWikiDataEntity(response, resourceId);
    }

    private WikiDataEntity extractWikiDataEntity(JsonNode response, String resourceId) {
        JsonNode entities = response.get("entities");
        if (entities.isNull()) {
            throw new WikiDataInvalidResponseException(resourceId);
        }
        JsonNode resourceEntity = entities.get(resourceId);
        if (resourceEntity.isNull()) {
            throw new WikiDataInvalidResponseException(resourceId);
        }
        return this.objectMapper.convertValue(resourceEntity, WikiDataEntity.class);

    }

    private String extractResourceIdOrThrow(String resourceUrl) throws MusicBrainInvalidWikiUrlException {
        if (resourceUrl == null) {
            throw new WikiDataMissingWikiUrlException();
        }

        Matcher matcher = URL_VALIDATION_PATTERN.matcher(resourceUrl);
        if (!matcher.matches()) {
            throw new MusicBrainInvalidWikiUrlException(resourceUrl);
        }
        return resourceUrl.replaceAll(".*/", "");


    }

    @Override
    protected String getServiceName() {
        return WIKI_DATA;
    }
}

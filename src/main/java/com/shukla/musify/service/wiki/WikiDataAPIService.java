package com.shukla.musify.service.wiki;

import com.shukla.musify.base.AMusifyRestTemplate;
import com.shukla.musify.service.musicbrains.pojo.Relation;
import com.shukla.musify.service.wiki.exception.MusicBrainInvalidWikiUrlException;
import com.shukla.musify.service.wiki.exception.WikiDataMissingWikiUrlException;
import com.shukla.musify.service.wiki.pojo.WikiData;
import com.shukla.musify.service.wiki.pojo.WikiDataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WikiDataAPIService extends AMusifyRestTemplate {

    private static final String VALIDATION_MATCH = "https://www\\.wikidata\\.org/wiki/[A-Za-z0-9_-]*$";
    private static final Pattern URL_VALIDATION_PATTERN = Pattern.compile(VALIDATION_MATCH);
    private static final String WIKI_DATA_API_URL = "https://www.wikidata.org/wiki/Special:EntityData/";

    public WikiData fetchWikiData(Relation wikiData) throws MusicBrainInvalidWikiUrlException {
        String resourceUrl = wikiData.getUrl().get("resource");
        extractResourceIdOrThrow(resourceUrl);
        return null;
    }

    private void extractResourceIdOrThrow(String resourceUrl) throws MusicBrainInvalidWikiUrlException {
        if (resourceUrl == null) {
            throw new WikiDataMissingWikiUrlException();
        }

        Matcher matcher = URL_VALIDATION_PATTERN.matcher(resourceUrl);
        if (!matcher.matches()) {
            throw new MusicBrainInvalidWikiUrlException();
        }
        String lastSegment = resourceUrl.replaceAll(".*/", "");
        String fetchQuery = WIKI_DATA_API_URL + lastSegment + ".json";
        ResponseEntity<WikiDataResponse> response = this.restTemplate.getForEntity(fetchQuery, WikiDataResponse.class);

    }
}

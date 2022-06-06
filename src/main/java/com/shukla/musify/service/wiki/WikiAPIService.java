package com.shukla.musify.service.wiki;

import com.shukla.musify.service.musicbrains.pojo.Relation;
import com.shukla.musify.service.wiki.exception.MusicBrainInvalidWikiUrlException;
import com.shukla.musify.service.wiki.exception.WikiDataMissingSiteLinkException;
import com.shukla.musify.service.wiki.pojo.WikiDataEntity;
import com.shukla.musify.service.wiki.pojo.WikiSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WikiAPIService {

    public static final String ENWIKI = "enwiki";
    private final WikiDataAPIService wikiDataAPIService;
    private final WikipediaAPIService wikipediaAPIService;


    @Autowired
    public WikiAPIService(WikiDataAPIService wikiDataAPIService, WikipediaAPIService wikipediaAPIService) {
        this.wikiDataAPIService = wikiDataAPIService;
        this.wikipediaAPIService = wikipediaAPIService;
    }

    public String getDescription(Relation relation) throws MusicBrainInvalidWikiUrlException {
        WikiDataEntity wikiDataResponse = this.wikiDataAPIService.fetchWikiData(relation);
        WikiSite enWiki = wikiDataResponse.getSiteLinks().get(ENWIKI);
        if (enWiki == null) {
            throw new WikiDataMissingSiteLinkException();
        }
        return this.wikipediaAPIService.fetchWikiDescription(enWiki);
    }

}

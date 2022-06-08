package com.shukla.musify.service.wiki;

import com.shukla.musify.base.AMusifyRestTemplate;
import com.shukla.musify.service.wiki.pojo.WikiSite;
import com.shukla.musify.service.wiki.pojo.WikipediaResponse;
import org.springframework.stereotype.Service;

@Service
public class WikipediaAPIService extends AMusifyRestTemplate<WikipediaResponse> {
    private static final String WIKI_BASE_URL = "https://en.wikipedia.org/api/rest_v1/page/summary/";
    
    public String fetchWikiDescription(WikiSite wikiSite) {
        String title = wikiSite.getTitle();
        String fetchQuery = WIKI_BASE_URL + title;
        WikipediaResponse response = this.getForEntity(fetchQuery, WikipediaResponse.class);
        return response.getDescription() + response.getExtractHtml();
    }
}

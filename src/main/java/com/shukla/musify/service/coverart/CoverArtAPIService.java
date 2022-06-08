package com.shukla.musify.service.coverart;

import com.shukla.musify.base.AMusifyRestTemplate;
import com.shukla.musify.service.coverart.pojo.CoverArtAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.Future;

@Service
public class CoverArtAPIService extends AMusifyRestTemplate<CoverArtAPIResponse> {
    private static final String COVER_ART_BASE_URL = "http://coverartarchive.org/release-group/";

    private final AsyncTaskExecutor asyncTaskExecutor;

    @Autowired
    public CoverArtAPIService(AsyncTaskExecutor asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @Cacheable(value = "albumCovers")
    public Future<String> fetchCoverArtASync(UUID mbid) {
        return this.asyncTaskExecutor.submit(() -> this.fetchCoverArtForSingleReleaseGroup(mbid));
    }

    private String fetchCoverArtForSingleReleaseGroup(UUID mbid) {
        String fetchQuery = COVER_ART_BASE_URL + mbid;
        CoverArtAPIResponse responseBody = this.getForEntity(fetchQuery, CoverArtAPIResponse.class);
        return responseBody.getImages().get(0).getImage();
    }
}

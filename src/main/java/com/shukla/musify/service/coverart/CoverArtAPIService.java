package com.shukla.musify.service.coverart;

import com.shukla.musify.base.AMusifyRestTemplate;
import com.shukla.musify.service.coverart.pojo.CoverArtAPIResponse;
import com.shukla.musify.service.musicbrains.pojo.ReleaseGroup;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CoverArtAPIService extends AMusifyRestTemplate<CoverArtAPIResponse> {
    private static final String COVER_ART_BASE_URL = "http://coverartarchive.org/release-group/";

    public Map<UUID, String> fetchCoverArtForReleaseGroups(List<ReleaseGroup> releaseGroups) {
        Map<UUID, String> map = new HashMap<>();
        releaseGroups.forEach(e -> {
            map.put(e.getId(), this.fetchCoverArtForSingleReleaseGroup(e.getId()));
        });
        return map;
    }

    private String fetchCoverArtForSingleReleaseGroup(UUID mbid) {
        String fetchQuery = COVER_ART_BASE_URL + mbid;
        CoverArtAPIResponse responseBody = this.getForEntity(fetchQuery, CoverArtAPIResponse.class);
        return responseBody.getImages().get(0).getImage();
    }
}

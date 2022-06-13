package com.shukla.musify.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shukla.musify.base.exception.MusifyNoResponseException;
import com.shukla.musify.service.ArtistInfoProviderService;
import com.shukla.musify.service.musicbrains.exception.MusicBrainWikiRelationNotFoundException;
import com.shukla.musify.service.pojo.ArtistInfo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtistWebAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Value("classpath:sampleResponse.json")
    private Resource sampleJsonResponse;
    private static final String TEST_ARTIST = "f27ec8db-af05-4f36-916e-3d57f91ecf5e";

    @MockBean
    private ArtistInfoProviderService artistInfoProviderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnSuccess() throws Exception {
        byte[] sampleResponseStream = this.sampleJsonResponse.getInputStream().readAllBytes();
        String expectedJsonResponse = new String(
                sampleResponseStream);
        ArtistInfo artistInfo = this.objectMapper.readValue(sampleResponseStream, ArtistInfo.class);

        when(this.artistInfoProviderService.getArtistInfo(UUID.fromString(TEST_ARTIST))).thenReturn(artistInfo);

        this.mockMvc.perform(get("/musify/music-artist/details/" + TEST_ARTIST))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResponse));
        verify(this.artistInfoProviderService, times(1)).getArtistInfo(UUID.fromString(TEST_ARTIST));
    }

    @Test
    public void shouldReturnUnProcessableRequest() throws Exception {
        when(this.artistInfoProviderService.getArtistInfo(UUID.fromString(TEST_ARTIST))).thenThrow(new MusicBrainWikiRelationNotFoundException());

        this.mockMvc.perform(get("/musify/music-artist/details/" + TEST_ARTIST))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.errorCode").value("[MusifyErrorCode -100]"))
                .andExpect(jsonPath("$.message").value("Cannot process this request"))
                .andExpect(jsonPath("$.errorUuid").value(Matchers.any(String.class)));
        verify(this.artistInfoProviderService, times(1)).getArtistInfo(UUID.fromString(TEST_ARTIST));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        when(this.artistInfoProviderService.getArtistInfo(UUID.fromString(TEST_ARTIST))).thenThrow(new MusifyNoResponseException("MusicBrain"));

        this.mockMvc.perform(get("/musify/music-artist/details/" + TEST_ARTIST))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("[MusifyErrorCode -2]"))
                .andExpect(jsonPath("$.message").value("Failed to retrieve data for this query"))
                .andExpect(jsonPath("$.errorUuid").value(Matchers.any(String.class)));
        verify(this.artistInfoProviderService, times(1)).getArtistInfo(UUID.fromString(TEST_ARTIST));
    }
}

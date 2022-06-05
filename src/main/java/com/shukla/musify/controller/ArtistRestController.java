package com.shukla.musify.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistRestController {

    @GetMapping("/")
    String getArtist(){
        return "Hello";
    }
}

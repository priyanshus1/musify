package com.shukla.musify.service.pojo;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/*
{
"id": "500d9b05-68c3-3535-86e3-cf685869efc0",
"title": "Farewell My Summer Love",
"imageUrl": "http://coverartarchive.org/release/8172928a-a6c7-
4d7c-83c8-5db2a4575094/13404444760.jpg"
*/
public class Album {

    private final UUID id;
    private final String title;
    private final Future<String> imageUrl;

    public Album(UUID id, String title, Future<String> imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImageUrl() throws ExecutionException, InterruptedException {
        return this.imageUrl.get();
    }
}

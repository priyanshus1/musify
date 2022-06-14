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

    private UUID id;
    private String title;
    private String imageUrl;

    private Album() {
    }

    public Album(UUID id, String title, Future<String> imageUrl) throws ExecutionException, InterruptedException {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl.get();
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
}

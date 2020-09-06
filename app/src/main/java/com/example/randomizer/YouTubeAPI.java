package com.example.randomizer;

import android.os.StrictMode;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class YouTubeAPI {
    // You need to set this value for your code to compile.
    // For example: ... DEVELOPER_KEY = "YOUR ACTUAL KEY";
    private static final String DEVELOPER_KEY = "AIzaSyBnnANRSFiHaDaYX6DOE4jZID_j_YD80sQ";

    private static final String APPLICATION_NAME = "com.example.randomizer";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService(){
        final NetHttpTransport httpTransport = new com.google.api.client.http.javanet.NetHttpTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static String getId(long maxResults)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException{
        YouTube youtubeService = getService();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Define and execute the API request
        YouTube.Videos.List request = youtubeService.videos().list(Collections.singletonList("id"));
        VideoListResponse response = request.setKey(DEVELOPER_KEY)
                .setMaxResults(maxResults)
                .setChart("mostPopular")
                .execute();
        List<Video> list = response.getItems();
        Random random = new Random();
        String id = list.get(random.nextInt(49)).getId();
        return id;
    }
}
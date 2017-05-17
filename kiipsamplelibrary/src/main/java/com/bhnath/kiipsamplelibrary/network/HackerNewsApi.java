package com.bhnath.kiipsamplelibrary.network;

import com.bhnath.kiipsamplelibrary.model.HackerNewsItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Wrapper for https://github.com/HackerNews/API
 * Uses Retrofit interfaces for ease of grabbing JSON from the API.
 */

public class HackerNewsApi {

    private static final String ENDPOINT =
            "https://hacker-news.firebaseio.com/v0/";

    private static HackerNewsApiInterface hackerNewsApiInterface;

    public static HackerNewsApiInterface getHackerNewsApiClient() {
        if (hackerNewsApiInterface == null) {
            Gson gson = new GsonBuilder()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            hackerNewsApiInterface = retrofit.create(HackerNewsApiInterface.class);
        }
        return hackerNewsApiInterface;
    }


    public interface HackerNewsApiInterface {
        @GET("topstories.json")
        Call<int[]> getTopStories();

        @GET("item/{id}.json")
        Call<HackerNewsItem> getStory(@Path("id") int storyId);
    }

}

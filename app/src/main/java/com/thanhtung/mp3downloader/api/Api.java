package com.thanhtung.mp3downloader.api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("search")
    Call<ApiResult> getVideo(
            @Query("part") String part,
            @Query("q") String query,
            @Query("type") String type,
            @Query("key") String apiKey,
            @Query("maxResults") int maxResults);
}

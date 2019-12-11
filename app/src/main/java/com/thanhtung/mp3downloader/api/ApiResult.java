package com.thanhtung.mp3downloader.api;

import com.google.gson.annotations.SerializedName;
import com.thanhtung.mp3downloader.model.youtubemodel.Item;

import java.util.List;

public class ApiResult {
    @SerializedName("kind")
    private String kind;
    @SerializedName("etag")
    private String etag;
    @SerializedName("nextPageToken")
    private String nextPageToken;
    @SerializedName("regionCode")
    private String regionCode;
    @SerializedName("items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

}

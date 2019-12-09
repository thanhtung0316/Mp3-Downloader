package com.thanhtung.mp3downloader.api;

import com.google.gson.annotations.SerializedName;
import com.thanhtung.mp3downloader.model.youtubemodel.Item;

import java.util.List;

public class ApiResult {
    @SerializedName("kind")
    public String kind;
    @SerializedName("etag")
    public String etag;
    @SerializedName("nextPageToken")
    public String nextPageToken;
    @SerializedName("regionCode")
    public String regionCode;
    @SerializedName("items")
    public List<Item> items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

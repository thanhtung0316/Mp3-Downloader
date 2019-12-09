package com.thanhtung.mp3downloader.model.youtubemodel;

import com.google.gson.annotations.SerializedName;
import com.thanhtung.mp3downloader.model.BaseModel;

public class Item extends BaseModel {
    @SerializedName("kind")
    public String kind;
    @SerializedName("etag")
    public String etag;
    @SerializedName("id")
    public VideoId id;
    @SerializedName("snippet")
    public Snippet snippet;


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

    public VideoId getId() {
        return id;
    }

    public void setId(VideoId id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}

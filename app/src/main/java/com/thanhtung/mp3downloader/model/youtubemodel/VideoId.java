package com.thanhtung.mp3downloader.model.youtubemodel;

import com.google.gson.annotations.SerializedName;

public class VideoId {

        @SerializedName("kind")
        public String kind;

        @SerializedName("videoId")
        public String videoId;


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}

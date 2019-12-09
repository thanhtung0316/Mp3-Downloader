package com.thanhtung.mp3downloader.model.youtubemodel;

import com.google.gson.annotations.SerializedName;

public class VideoThumbnails {
    @SerializedName("high")
    public QualityImageThumbnail high;

    public QualityImageThumbnail getHigh() {
        return high;
    }

    public void setHigh(QualityImageThumbnail high) {
        this.high = high;
    }
}

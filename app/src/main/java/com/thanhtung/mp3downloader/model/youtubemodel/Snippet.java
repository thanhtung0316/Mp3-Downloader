package com.thanhtung.mp3downloader.model.youtubemodel;

import com.google.gson.annotations.SerializedName;

public class Snippet {
    @SerializedName("publishedAt")
    public String publishedAt;
    @SerializedName("channelId")
    public String channelId;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("thumbnails")
    public VideoThumbnails thumbnails;
    @SerializedName("channelTitle")
    public String channelTitle;
    @SerializedName("liveBroadcastContent")
    public String liveBroadcastContent;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VideoThumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(VideoThumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }
}

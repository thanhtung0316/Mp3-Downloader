package com.thanhtung.mp3downloader.model;

public class Song {
    private String songName;
    private String songArtist;
    private String songQuality;
    private String songViews;
    private String imageLink;
    private String linkDetail;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLinkDetail() {
        return linkDetail;
    }

    public void setLinkDetail(String linkDetail) {
        this.linkDetail = linkDetail;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongQuality() {
        return songQuality;
    }

    public void setSongQuality(String songQuality) {
        this.songQuality = songQuality;
    }

    public String getSongViews() {
        return songViews;
    }

    public void setSongViews(String songViews) {
        this.songViews = songViews;
    }
}

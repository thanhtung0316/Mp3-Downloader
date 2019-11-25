package com.thanhtung.mp3downloader.model;

public class SongDetail extends Song {

    private String songDownloadLink;
    private String songLyric;
    private String songPubYear;
    private String songComposer;

    public String getSongDownloadLink() {
        return songDownloadLink;
    }

    public void setSongDownloadLink(String songDownloadLink) {
        this.songDownloadLink = songDownloadLink;
    }

    public String getSongLyric() {
        return songLyric;
    }

    public void setSongLyric(String songLyric) {
        this.songLyric = songLyric;
    }

    public String getSongPubYear() {
        return songPubYear;
    }

    public void setSongPubYear(String songPubYear) {
        this.songPubYear = songPubYear;
    }

    public String getSongComposer() {
        return songComposer;
    }

    public void setSongComposer(String songComposer) {
        this.songComposer = songComposer;
    }
}

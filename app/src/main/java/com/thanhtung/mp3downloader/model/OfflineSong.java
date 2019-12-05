package com.thanhtung.mp3downloader.model;

import android.provider.MediaStore;


public class OfflineSong extends BaseModel {
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.DATE_ADDED)
    private String data;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.TITLE)
    private String title;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.SIZE)
    private String size;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.DURATION)
    private int duration;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.ARTIST)
    private String artist;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.ALBUM)
    private String album;



    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public String getSize() {
        return size;
    }

    public int getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}

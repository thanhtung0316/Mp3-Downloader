package com.thanhtung.mp3downloader.model;

import android.provider.MediaStore;


public class OfflineSong {
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.DATE_ADDED)
    private String data;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.TITLE)
    private String title;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.SIZE)
    private String size;
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.DURATION)
    private String duration;
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

    public String getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }
}

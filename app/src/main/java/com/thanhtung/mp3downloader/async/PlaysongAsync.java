package com.thanhtung.mp3downloader.async;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.MemoryFile;

import java.io.IOException;

public class PlaysongAsync extends AsyncTask<String, String, String> {
    private MediaPlayer mediaPlayer;
    private int mediaFileLength;
    private int realtimeLength;

    @Override
    protected String doInBackground(String... strings) {
        try {
            mediaPlayer.setDataSource(strings[0]);
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        mediaFileLength = mediaPlayer.getDuration();
        realtimeLength = mediaFileLength;
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();

        }
    }
}

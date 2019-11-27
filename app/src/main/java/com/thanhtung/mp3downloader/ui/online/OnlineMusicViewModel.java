package com.thanhtung.mp3downloader.ui.online;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thanhtung.mp3downloader.async.SearchAsync;
import com.thanhtung.mp3downloader.model.Song;

import java.util.ArrayList;
import java.util.List;

public class OnlineMusicViewModel extends ViewModel implements SearchAsync.SearchTaskCallBack {

    private MutableLiveData<List<Song>> songs;

    public LiveData<List<Song>> getSongs() {
        if (songs==null){
            songs = new MutableLiveData<>();
            loadSong();
        }
        return songs;
    }

    private void loadSong() {
        SearchAsync async = new SearchAsync();
        async.setCallback(this);
        async.execute("https://chiasenhac.vn/tim-kiem?q=hello");
    }

    public void setSongs(MutableLiveData<List<Song>> songs) {
        this.songs = songs;
    }

    @Override
    public void OnSearchTaskComplete(ArrayList<Song> songList) {
        songs.setValue(songList);
    }
}

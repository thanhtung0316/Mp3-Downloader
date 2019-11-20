package com.thanhtung.mp3downloader.ui.online;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thanhtung.mp3downloader.model.Song;

import java.util.List;

public class OnlineMusicViewModel extends ViewModel {

    private MutableLiveData<List<Song>> songs;

    public MutableLiveData<List<Song>> getSongs() {
        if (songs==null){
            songs = new MutableLiveData<>();
            fetchData();
        }
        return songs;
    }

    private void fetchData() {

    }
}
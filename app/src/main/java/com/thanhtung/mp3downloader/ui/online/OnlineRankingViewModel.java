package com.thanhtung.mp3downloader.ui.online;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thanhtung.mp3downloader.async.OnlineRankingAsync;
import com.thanhtung.mp3downloader.model.Song;

import java.util.List;

public class OnlineRankingViewModel extends ViewModel implements OnlineRankingAsync.OnRankingSongCallback {
    private String baseLink = "https://chiasenhac.vn";
    private MutableLiveData<List<List<Song>>> songs;

    public LiveData<List<List<Song>>> getSongs() {
        if (songs==null){
            songs = new MutableLiveData<>();
            loadSong();
        }
        return songs;
    }

    private void loadSong() {
        OnlineRankingAsync async = new OnlineRankingAsync();
        async.execute(baseLink);
        async.setmCallback(this);
    }

    @Override
    public void onGetRankingComplete(List<List<Song>> lists) {
        songs.postValue(lists);
    }
}

package com.thanhtung.mp3downloader.ui.online.search;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thanhtung.mp3downloader.async.SearchAsync;
import com.thanhtung.mp3downloader.model.Song;
import java.util.ArrayList;
import java.util.List;

public class OnlineSearchMusicViewModel extends ViewModel implements SearchAsync.SearchTaskCallBack {

    private MutableLiveData<List<Song>> songs;
    private String linkToRequest;
    private ArrayList<Song> songArrayList;


    public void setLinkToRequest(String linkToRequest) {
        this.linkToRequest = linkToRequest;
        fetchData(linkToRequest);
    }

    public MutableLiveData<List<Song>> getSongs() {
        if (songs==null){
            songs = new MutableLiveData<>();
            fetchData(linkToRequest);
        }
        return songs;
    }

    private void fetchData(String url) {
        songArrayList = new ArrayList<>();
        SearchAsync async = new SearchAsync();
        async.setmCallback(this);
        async.execute(url);
    }
    @Override
    public void OnSearchTaskComplete(ArrayList<Song> songArrayList) {
        songs.setValue(songArrayList);
        Log.e("LOG","Size: "+songArrayList.size());
    }
}
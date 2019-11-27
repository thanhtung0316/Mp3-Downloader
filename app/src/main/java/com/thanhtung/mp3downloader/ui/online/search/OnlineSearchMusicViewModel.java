package com.thanhtung.mp3downloader.ui.online.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thanhtung.mp3downloader.async.SearchAsync;
import com.thanhtung.mp3downloader.model.Song;

import java.util.ArrayList;
import java.util.List;

public class OnlineSearchMusicViewModel extends ViewModel implements SearchAsync.SearchTaskCallBack {

    private MutableLiveData<List<Song>> songs;
    private MutableLiveData<Song> itemSongSelected;
    private ArrayList<Song> songArrayList;
    private String linkToRequest;


    public MutableLiveData<Song> getItemSongSelected() {
        if (itemSongSelected==null){
            itemSongSelected = new MutableLiveData<>();
        }
        return itemSongSelected;
    }

    protected void setItemSongSelected(Song songSelected) {
        if (itemSongSelected==null){
            itemSongSelected = new MutableLiveData<>();
        }
        itemSongSelected.postValue(songSelected);
    }

    public void setLinkToRequest(String linkToRequest) {
        this.linkToRequest = linkToRequest;
        fetchData(linkToRequest);
    }

    public LiveData<List<Song>> getSongs() {
        if (songs == null) {
            songs = new MutableLiveData<>();
            fetchData(linkToRequest);
        }
        return songs;
    }

    private void fetchData(String linkToRequest) {
        songArrayList = new ArrayList<>();
        SearchAsync async = new SearchAsync();
        async.setCallback(this);
        async.execute(linkToRequest);
    }

    @Override
    public void OnSearchTaskComplete(ArrayList<Song> songArrayList) {
        songs.postValue(songArrayList);
    }
}
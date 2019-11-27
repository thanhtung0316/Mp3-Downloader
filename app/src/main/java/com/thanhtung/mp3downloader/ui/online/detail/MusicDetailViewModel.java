package com.thanhtung.mp3downloader.ui.online.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.thanhtung.mp3downloader.async.GetSongDetailAsync;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.model.SongDetail;


public class MusicDetailViewModel extends ViewModel implements GetSongDetailAsync.GetSongDetailCallBack {

    private MutableLiveData<SongDetail> songDetail;
    private Song song;


    public void setSong(Song song) {
        this.song = song;
        fetchData(song);
    }

    public MutableLiveData<SongDetail> getSongDetail() {
        if (songDetail==null){
            songDetail = new MutableLiveData<>();
            fetchData(song);
        }
        return songDetail;
    }

    private void fetchData(Song song) {
        GetSongDetailAsync async = new GetSongDetailAsync();
        async.setmCallback(this);
        async.execute(song);
    }

    @Override
    public void OnGetSongDetailComplete(SongDetail song) {
        songDetail.setValue(song);
    }
}

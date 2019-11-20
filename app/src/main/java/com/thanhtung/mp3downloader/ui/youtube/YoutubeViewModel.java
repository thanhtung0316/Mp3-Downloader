package com.thanhtung.mp3downloader.ui.youtube;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YoutubeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public YoutubeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
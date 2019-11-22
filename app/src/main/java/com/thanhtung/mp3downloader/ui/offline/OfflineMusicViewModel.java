package com.thanhtung.mp3downloader.ui.offline;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OfflineMusicViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OfflineMusicViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
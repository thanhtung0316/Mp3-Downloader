package com.thanhtung.mp3downloader.ui.offline;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thanhtung.mp3downloader.SystemDataUtils;
import com.thanhtung.mp3downloader.model.OfflineSong;

import java.util.List;

public class OfflineMusicViewModel extends ViewModel {

    private MutableLiveData<List<OfflineSong>> data;
    private SystemDataUtils dataUtils;


    public MutableLiveData<List<OfflineSong>> getData(Context context) {
        if (data == null) {
            data = new MutableLiveData<>();
            dataUtils = new SystemDataUtils(context);
            data.setValue(dataUtils.getSongs());
        }
        return data;
    }

    public void setData(MutableLiveData<List<OfflineSong>> data) {
        this.data = data;
    }
}
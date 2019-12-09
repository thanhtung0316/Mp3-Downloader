package com.thanhtung.mp3downloader.ui.youtube;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thanhtung.mp3downloader.api.ApiBuilder;
import com.thanhtung.mp3downloader.api.ApiResult;
import com.thanhtung.mp3downloader.model.youtubemodel.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchVideoViewModel extends ViewModel implements Callback<ApiResult> {
    private MutableLiveData<List<Item>> data;
    private final String part = "snippet";
    private final String type = "video";
    private final String apiKey = "AIzaSyAIWg1CspES-FBcfSzcddsR98vMDkFCqPk";
    private final int maxResults =50;
    private String keySearch;


    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
        fetchData(keySearch);
    }

    public MutableLiveData<List<Item>> getData() {
        if (data==null){
            data = new MutableLiveData<>();
            fetchData(keySearch);
        }
        return data;
    }

    private void fetchData(String keySearch) {
        if (!(keySearch ==null)){
            ApiBuilder.getInstance().getVideo(part,keySearch,type,apiKey,maxResults).enqueue(this);
        }
    }


    @Override
    public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
        if (response.body()!=null){
            List<Item> items = response.body().getItems();
            data.postValue(items);
            Log.e("TAG","CALL API");
        } else {
            Log.e("TAG","ERROR");
        }
    }

    @Override
    public void onFailure(Call<ApiResult> call, Throwable t) {
        Log.e("TAG","FAIL: "+t.getMessage());
    }
}
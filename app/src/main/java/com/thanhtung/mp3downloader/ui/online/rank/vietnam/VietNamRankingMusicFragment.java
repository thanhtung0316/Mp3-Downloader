package com.thanhtung.mp3downloader.ui.online.rank.vietnam;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.thanhtung.mp3downloader.BaseFragment;
import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.databinding.FragmentVietnamRankingMusicBinding;

public class VietNamRankingMusicFragment extends BaseFragment<FragmentVietnamRankingMusicBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vietnam_ranking_music;
    }

    @Override
    public String getTitle() {
        return "Việt Nam";
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}

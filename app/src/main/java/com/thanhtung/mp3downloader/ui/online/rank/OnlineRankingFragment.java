package com.thanhtung.mp3downloader.ui.online.rank;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thanhtung.mp3downloader.R;

import com.thanhtung.mp3downloader.adapter.RankingPagerAdapter;
import com.thanhtung.mp3downloader.adapter.SongAdapter;

import com.thanhtung.mp3downloader.databinding.FragmentOnlineRankingBinding;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.ui.online.OnlineMusicFragment;
import com.thanhtung.mp3downloader.ui.online.OnlineRankingViewModel;


import java.util.List;

public class OnlineRankingFragment extends Fragment implements View.OnClickListener {
    private OnlineRankingViewModel viewModel;
    private String TAG = "OnlineRankingMusicFragment";
    private SongAdapter adapter;
    private FragmentOnlineRankingBinding binding;
    private OnlineMusicFragment fmOnline;
    private RankingPagerAdapter pagerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_online_ranking,container,false);
        fmOnline = (OnlineMusicFragment) getParentFragment();
        binding.imvSearch.setOnClickListener(this);

        pagerAdapter = new RankingPagerAdapter(getChildFragmentManager(),1);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabMode.setupWithViewPager(binding.viewPager);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        fmOnline.showFragment(fmOnline.getFmSearch(),android.R.anim.slide_in_left,android.R.anim.slide_in_left);
    }

}
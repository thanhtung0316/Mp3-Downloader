package com.thanhtung.mp3downloader.ui.online.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.adapter.SongDetailPagerAdapter;
import com.thanhtung.mp3downloader.databinding.FragmentSongDetailBinding;

public class MusicDetailFragment extends Fragment {
    private FragmentSongDetailBinding binding;
    private SongDetailPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_detail, container, false);
        adapter = new SongDetailPagerAdapter(getChildFragmentManager());
        binding.pager.setAdapter(adapter);
        binding.tabDots.setupWithViewPager(binding.pager);
        return binding.getRoot();
    }
}

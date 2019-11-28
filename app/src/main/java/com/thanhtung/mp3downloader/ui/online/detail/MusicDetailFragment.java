package com.thanhtung.mp3downloader.ui.online.detail;

import android.content.Context;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.adapter.SongDetailPagerAdapter;
import com.thanhtung.mp3downloader.databinding.FragmentSongDetailBinding;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.model.SongDetail;
import com.thanhtung.mp3downloader.ui.online.OnlineMusicFragment;
import com.thanhtung.mp3downloader.ui.online.search.OnlineSearchMusicViewModel;

public class MusicDetailFragment extends Fragment implements View.OnClickListener {
    private FragmentSongDetailBinding binding;
    private SongDetailPagerAdapter adapter;
    private OnlineMusicFragment fmOnline;
    private MusicDetailViewModel musicDetailViewModel;
    private OnlineSearchMusicViewModel searchMusicViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_detail, container, false);
        fmOnline = (OnlineMusicFragment) getParentFragment();
        adapter = new SongDetailPagerAdapter(getChildFragmentManager());
        binding.pager.setAdapter(adapter);
        binding.circleIndicator.setViewPager(binding.pager);
        binding.imvBack.setOnClickListener(this);
        getSongDetail();
        getItemSongSelected();
        return binding.getRoot();
    }


    @Override
    public void onClick(View v) {
        fmOnline.showFragment(fmOnline.getFmSearch(), android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void getItemSongSelected() {
        searchMusicViewModel = ViewModelProviders.of(requireActivity()).get(OnlineSearchMusicViewModel.class);
        searchMusicViewModel.getItemSongSelected().observe(getViewLifecycleOwner(), new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                binding.setItem(song);
                musicDetailViewModel.setSong(song);
            }
        });
    }

    private void getSongDetail() {
        musicDetailViewModel = ViewModelProviders.of(requireActivity()).get(MusicDetailViewModel.class);
        musicDetailViewModel.getSongDetail().observe(getViewLifecycleOwner(), new Observer<SongDetail>() {
            @Override
            public void onChanged(SongDetail songDetail) {
                Log.e("TAG","");
            }
        });
    }
}

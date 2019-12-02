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
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.BaseRequestOptions;
import com.thanhtung.mp3downloader.BlurTransformation;
import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.databinding.FragmentPlaySongBinding;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.ui.online.search.OnlineSearchMusicViewModel;

public class PlaySongFragment extends Fragment {
    private FragmentPlaySongBinding binding;
    private OnlineSearchMusicViewModel searchMusicViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_song, container, false);

        searchMusicViewModel = ViewModelProviders.of(requireActivity()).get(OnlineSearchMusicViewModel.class);
        searchMusicViewModel.getItemSongSelected().observe(getViewLifecycleOwner(), new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                loadImage(song.getImageLink());
            }
        });

        return binding.getRoot();
    }

    public void loadImage(String imgLink) {
        Glide.with(binding.imvSong)
                .load(imgLink)
                .into(binding.imvSong);

        Glide.with(binding.imvBackground)
                .load(imgLink)
                .transform( new BlurTransformation(getContext()))
                .into(binding.imvBackground);
    }


}

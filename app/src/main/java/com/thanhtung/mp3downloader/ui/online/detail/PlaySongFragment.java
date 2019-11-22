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
import com.thanhtung.mp3downloader.databinding.FragmentPlaySongBinding;

public class PlaySongFragment extends Fragment {
    private FragmentPlaySongBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_song,container,false);
        return binding.getRoot();
    }
}

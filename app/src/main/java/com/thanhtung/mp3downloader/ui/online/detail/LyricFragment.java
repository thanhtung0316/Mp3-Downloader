package com.thanhtung.mp3downloader.ui.online.detail;

import android.os.Bundle;
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
import com.thanhtung.mp3downloader.databinding.FragmentLyricBinding;
import com.thanhtung.mp3downloader.model.SongDetail;

public class LyricFragment extends Fragment {
    private FragmentLyricBinding binding;
    private MusicDetailViewModel musicDetailViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lyric,container,false);
        musicDetailViewModel = ViewModelProviders.of(requireActivity()).get(MusicDetailViewModel.class);
        musicDetailViewModel.getSongDetail().observe(getViewLifecycleOwner(), new Observer<SongDetail>() {
            @Override
            public void onChanged(SongDetail songDetail) {
                binding.tvLyric.setText(songDetail.getSongLyric());
            }
        });


        return binding.getRoot();
    }
}

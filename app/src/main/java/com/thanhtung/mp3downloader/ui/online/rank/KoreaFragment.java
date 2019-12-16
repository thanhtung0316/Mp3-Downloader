package com.thanhtung.mp3downloader.ui.online.rank;

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
import com.thanhtung.mp3downloader.adapter.BaseAdapter;
import com.thanhtung.mp3downloader.adapter.SongAdapter;
import com.thanhtung.mp3downloader.databinding.FragmentKoreaRankingMusicBinding;
import com.thanhtung.mp3downloader.databinding.FragmentVietnamRankingMusicBinding;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.ui.online.OnlineRankingViewModel;

import java.util.List;

public class KoreaFragment extends Fragment {
    private FragmentKoreaRankingMusicBinding binding;
    private OnlineRankingViewModel viewModel;
    private SongAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_korea_ranking_music,container,false);
        adapter = new SongAdapter(getContext());
        viewModel = ViewModelProviders.of(requireActivity()).get(OnlineRankingViewModel.class);
        viewModel.getKoreaRanking().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                adapter.setData(songs);
                binding.rcvSong.setAdapter(adapter);
            }
        });

        return binding.getRoot();
    }
}

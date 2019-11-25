package com.thanhtung.mp3downloader.ui.online.detail;

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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.adapter.SongDetailPagerAdapter;
import com.thanhtung.mp3downloader.databinding.FragmentSongDetailBinding;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.model.SongDetail;
import com.thanhtung.mp3downloader.ui.online.OnlineMusicFragment;

public class MusicDetailFragment extends Fragment implements View.OnClickListener {
    private FragmentSongDetailBinding binding;
    private SongDetailPagerAdapter adapter;
    private OnlineMusicFragment fmOnline;
    private Song song;
    private MusicDetailViewModel viewModel;

    public void setSong(Song song) {
        this.song = song;
        bindData(song);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_detail, container, false);
        adapter = new SongDetailPagerAdapter(getChildFragmentManager());
        binding.pager.setAdapter(adapter);
        binding.circleIndicator.setViewPager(binding.pager);
        binding.backFragment.setOnClickListener(this);
        fmOnline = (OnlineMusicFragment) getParentFragment();
        return binding.getRoot();
    }

    private void bindData(Song song) {
        binding.setItem(song);
        viewModel = ViewModelProviders.of(this).get(MusicDetailViewModel.class);

        viewModel.getSongDetail().observe(this, new Observer<SongDetail>() {
            @Override
            public void onChanged(SongDetail songDetail) {
                Log.e("TAG","SongDetail: "+songDetail);
            }
        });
        viewModel.setSong(song);
    }

    @Override
    public void onClick(View v) {
        fmOnline.showFragment(fmOnline.getFmSearch(),android.R.anim.fade_in,android.R.anim.fade_out);
    }
}

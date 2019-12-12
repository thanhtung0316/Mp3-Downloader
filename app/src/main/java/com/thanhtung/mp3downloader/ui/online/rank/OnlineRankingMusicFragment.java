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

import com.thanhtung.mp3downloader.adapter.SongAdapter;

import com.thanhtung.mp3downloader.adapter.SongPagerAdapter;
import com.thanhtung.mp3downloader.databinding.FragmentOnlineRankingBinding;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.ui.online.OnlineMusicFragment;
import com.thanhtung.mp3downloader.ui.online.OnlineRankingViewModel;
import com.thanhtung.mp3downloader.ui.online.rank.korea.KoreaRankingMusicFragment;
import com.thanhtung.mp3downloader.ui.online.rank.usuk.UsUkRankingMusicFragment;
import com.thanhtung.mp3downloader.ui.online.rank.vietnam.VietNamRankingMusicFragment;

import java.util.List;

public class OnlineRankingMusicFragment extends Fragment implements View.OnClickListener {
    private OnlineRankingViewModel viewModel;
    private String TAG = "OnlineRankingMusicFragment";
    private SongAdapter adapter;
    private FragmentOnlineRankingBinding binding;
    private OnlineMusicFragment fmOnline;
    private SongPagerAdapter pagerAdapter;
    private VietNamRankingMusicFragment fmVietnamRanking = new VietNamRankingMusicFragment();
    private UsUkRankingMusicFragment fmUSUK = new UsUkRankingMusicFragment();
    private KoreaRankingMusicFragment fmKorea = new KoreaRankingMusicFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_online_ranking,container,false);
//        adapter = new SongAdapter(getContext());
//        fmOnline = (OnlineMusicFragment) getParentFragment();

//        binding.imvSearch.setOnClickListener(this);
//        viewModel = ViewModelProviders.of(requireActivity()).get(OnlineRankingViewModel.class);
//        viewModel.getSongs().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
//            @Override
//            public void onChanged(List<Song> songs) {
//                Log.e(TAG,"SIZE: "+songs.size());
//                adapter.setData(songs);
//                binding.rvOnlineSong.setAdapter(adapter);
//            }
//        });
        pagerAdapter = new SongPagerAdapter(getChildFragmentManager(),1,fmVietnamRanking,fmUSUK,fmKorea);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabMode.setupWithViewPager(binding.viewPager);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        fmOnline.showFragment(fmOnline.getFmSearch(),android.R.anim.slide_in_left,android.R.anim.slide_in_left);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        adapter = new SongAdapter(getContext());
//        viewModel = ViewModelProviders.of(this).get(OnlineRankingViewModel.class);
//        viewModel.getSongs().observe(this, new Observer<List<Song>>() {
//            @Override
//            public void onChanged(List<Song> songs) {
//                Log.e(TAG,"SIZE: "+songs.size());
//                adapter.setData(songs);
//                binding.rvOnlineSong.setAdapter(adapter);
//            }
//        });


//    }



}

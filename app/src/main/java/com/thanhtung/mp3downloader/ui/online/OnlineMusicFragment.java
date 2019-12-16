package com.thanhtung.mp3downloader.ui.online;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.databinding.FragmentOnlineMusicBinding;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.ui.online.detail.MusicDetailFragment;
import com.thanhtung.mp3downloader.ui.online.rank.OnlineRankingFragment;
import com.thanhtung.mp3downloader.ui.online.search.OnlineSearchMusicFragment;

import java.util.List;

public class OnlineMusicFragment extends Fragment {
    private FragmentOnlineMusicBinding binding;
    private OnlineSearchMusicFragment fmSearch = new OnlineSearchMusicFragment();
    private MusicDetailFragment fmDetail = new MusicDetailFragment();
    private OnlineRankingFragment fmRanking = new OnlineRankingFragment();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private OnlineRankingViewModel viewModel;
    private String TAG = "OnlineMusicFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_online_music, container, false);

        manager = getChildFragmentManager();
        transaction = manager.beginTransaction();
        if (manager.getFragments().isEmpty()) {
            init();
        }
        showFragment(fmRanking, android.R.anim.fade_in, android.R.anim.fade_out);
        viewModel = ViewModelProviders.of(requireActivity()).get(OnlineRankingViewModel.class);
        viewModel.getSongs().observe(getViewLifecycleOwner(), new Observer<List<List<Song>>>() {
            @Override
            public void onChanged(List<List<Song>> lists) {
                if (lists.size()!=0){
                    viewModel.setVnRanking(lists.get(0));
                    viewModel.setUsRanking(lists.get(1));
                    viewModel.setKoreaRanking(lists.get(2));
                }

            }
        });



        return binding.getRoot();
    }

    private void init() {
        transaction.add(R.id.ly_frame, fmSearch);
        transaction.add(R.id.ly_frame, fmDetail);
        transaction.add(R.id.ly_frame, fmRanking);
        transaction.commit();

    }

    public void showFragment(Fragment fmShow, int animationIn, int animationOut) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.hide(fmDetail);
        transaction.hide(fmSearch);
        transaction.hide(fmRanking);
        transaction.setCustomAnimations(animationIn, animationOut);
        transaction.show(fmShow);
        transaction.commit();
    }

    public OnlineSearchMusicFragment getFmSearch() {
        return fmSearch;
    }

    public MusicDetailFragment getFmDetail() {
        return fmDetail;
    }

    public OnlineRankingFragment getFmRanking() {
        return fmRanking;
    }
}

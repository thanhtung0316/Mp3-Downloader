package com.thanhtung.mp3downloader.ui.youtube;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.databinding.FragmentYoutubeBinding;

public class YoutubeFragment extends Fragment {
    private FragmentYoutubeBinding binding;
    private SearchVideoFragment fmVideoSearch = new SearchVideoFragment();
    private PlayVideoFragment fmPlayVideo = new PlayVideoFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_youtube, container, false);
        init();
        showFragment(fmVideoSearch,android.R.anim.fade_in, android.R.anim.fade_out);
        return binding.getRoot();
    }

    private void init() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.ly_frame, fmVideoSearch).add(R.id.ly_frame, fmPlayVideo);
        transaction.commit();
    }

    public void showFragment(Fragment fmShow, int animationIn, int animationOut) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.hide(fmPlayVideo).hide(fmVideoSearch).show(fmShow);
        transaction.setCustomAnimations(animationIn, animationOut);
        transaction.show(fmShow);
        transaction.commit();
    }


    public SearchVideoFragment getFmVideoSearch() {
        return fmVideoSearch;
    }

    public PlayVideoFragment getFmPlayVideo() {
        return fmPlayVideo;
    }
}

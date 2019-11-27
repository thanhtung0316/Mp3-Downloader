package com.thanhtung.mp3downloader.ui.online;

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
import com.thanhtung.mp3downloader.databinding.FragmentOnlineMusicBinding;
import com.thanhtung.mp3downloader.ui.online.detail.MusicDetailFragment;
import com.thanhtung.mp3downloader.ui.online.search.OnlineSearchMusicFragment;

public class OnlineMusicFragment extends Fragment {
    private FragmentOnlineMusicBinding binding;
    private OnlineSearchMusicFragment fmSearch = new OnlineSearchMusicFragment();
    private MusicDetailFragment fmDetail = new MusicDetailFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_online_music, container, false);
        init();
        showFragment(fmSearch, android.R.anim.fade_in, android.R.anim.fade_out);
        return binding.getRoot();
    }

    private void init() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.ly_frame, fmSearch);
        transaction.add(R.id.ly_frame, fmDetail);
        transaction.commit();
    }

    public void showFragment(Fragment fmShow, int animationIn, int animationOut) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.hide(fmDetail);
        transaction.hide(fmSearch);
        transaction.setCustomAnimations(animationIn, animationOut);
        transaction.show(fmShow);
        transaction.commit();
    }

    public OnlineSearchMusicFragment getFmSearch() {
        return fmSearch;
    }

    public void setFmSearch(OnlineSearchMusicFragment fmSearch) {
        this.fmSearch = fmSearch;
    }

    public MusicDetailFragment getFmDetail() {
        return fmDetail;
    }

    public void setFmDetail(MusicDetailFragment fmDetail) {
        this.fmDetail = fmDetail;
    }
}

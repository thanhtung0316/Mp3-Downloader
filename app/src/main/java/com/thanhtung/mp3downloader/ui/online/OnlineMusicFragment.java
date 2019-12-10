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

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.databinding.FragmentOnlineMusicBinding;
import com.thanhtung.mp3downloader.ui.online.detail.MusicDetailFragment;
import com.thanhtung.mp3downloader.ui.online.search.OnlineSearchMusicFragment;

public class OnlineMusicFragment extends Fragment {
    private FragmentOnlineMusicBinding binding;
    private OnlineSearchMusicFragment fmSearch = new OnlineSearchMusicFragment();
    private MusicDetailFragment fmDetail = new MusicDetailFragment();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_online_music, container, false);

        manager = getChildFragmentManager();
        transaction = manager.beginTransaction();
        if (manager.getFragments().isEmpty()){
            init();
        }

        showFragment(fmSearch, android.R.anim.fade_in, android.R.anim.fade_out);
        return binding.getRoot();
    }

    private void init() {
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

    public MusicDetailFragment getFmDetail() {
        return fmDetail;
    }

}

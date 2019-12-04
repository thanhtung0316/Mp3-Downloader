package com.thanhtung.mp3downloader.ui.offline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.SystemDataUtils;

public class OfflineMusicFragment extends Fragment {

    private OfflineMusicViewModel notificationsViewModel;
    private SystemDataUtils dataUtils;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(OfflineMusicViewModel.class);
        View root = inflater.inflate(R.layout.fragment_youtube, container, false);
        dataUtils = new SystemDataUtils(getContext());
        return root;
    }
}
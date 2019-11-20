package com.thanhtung.mp3downloader.ui.online;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.databinding.FragmentOnlineMusicBinding;

public class OnlineMusicFragment extends Fragment {
    private FragmentOnlineMusicBinding binding;
    private OnlineMusicViewModel musicViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_online_music,
                container, false);
        musicViewModel = ViewModelProviders.of(this).get(OnlineMusicViewModel.class);

        return binding.getRoot();
    }
}
package com.thanhtung.mp3downloader.ui.offline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.SystemDataUtils;
import com.thanhtung.mp3downloader.adapter.BaseSongAdapter;
import com.thanhtung.mp3downloader.databinding.FragmentOfflineMusicBinding;
import com.thanhtung.mp3downloader.model.OfflineSong;

import java.util.List;

public class OfflineMusicFragment extends Fragment implements BaseSongAdapter.BaseItemlistener {

    private OfflineMusicViewModel offlineMusicViewModel;
    private BaseSongAdapter<OfflineSong> adapter;
    private FragmentOfflineMusicBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_offline_music, container, false);
        adapter = new BaseSongAdapter<>(getContext(),R.layout.item_offline_song);
        adapter.setItemlistener(this);
        offlineMusicViewModel = ViewModelProviders.of(this).get(OfflineMusicViewModel.class);

        offlineMusicViewModel.getData(getContext()).observe(this, new Observer<List<OfflineSong>>() {
            @Override
            public void onChanged(List<OfflineSong> offlineSongs) {
                adapter.setData(offlineSongs);
                binding.rvOfflineSong.setAdapter(adapter);
                Log.e("TAG","SIZE: "+offlineSongs.size());
//                Log.e("TAG","SIZE: "+offlineSongs.get(1).getDuration());
            }
        });
        return binding.getRoot();
    }
}
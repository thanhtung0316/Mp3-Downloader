package com.thanhtung.mp3downloader.ui.offline;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import com.thanhtung.mp3downloader.service.PlaySongService;

import java.util.List;

public class OfflineMusicFragment extends Fragment implements OfflineSongListener {

    private OfflineMusicViewModel offlineMusicViewModel;
    private BaseSongAdapter<OfflineSong> adapter;
    private FragmentOfflineMusicBinding binding;
    private PlaySongService service;

    private final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_offline_music, container, false);
        adapter = new BaseSongAdapter<>(getContext(), R.layout.item_offline_song);
        offlineMusicViewModel = ViewModelProviders.of(this).get(OfflineMusicViewModel.class);
        adapter.setItemlistener(this);
        initViews();

        offlineMusicViewModel.getData(getContext()).observe(this, new Observer<List<OfflineSong>>() {
            @Override
            public void onChanged(List<OfflineSong> offlineSongs) {
                adapter.setData(offlineSongs);
                binding.rvOfflineSong.setAdapter(adapter);
                Log.e("TAG", "SIZE: " + offlineSongs.size());
//                Log.e("TAG","SIZE: "+offlineSongs.get(1).getDuration());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onSongClicked(OfflineSong offlineSong) {
        service.setData(adapter.getData());
        service.create(adapter.getData().indexOf(offlineSong));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initViews() {
        if (!checkPermission()) {
            return;
        }
        Intent intent = new Intent(getActivity(), PlaySongService.class);
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlaySongService.MP3Binder binder = (PlaySongService.MP3Binder) service;
            OfflineMusicFragment.this.service = binder.getService();
            binding.playView.setService(OfflineMusicFragment.this.service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : PERMISSIONS) {
                int check = getContext().checkSelfPermission(p);
                if (check != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unbindService(connection);
    }
}
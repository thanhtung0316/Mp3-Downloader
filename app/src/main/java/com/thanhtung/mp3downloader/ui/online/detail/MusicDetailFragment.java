package com.thanhtung.mp3downloader.ui.online.detail;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.adapter.SongDetailPagerAdapter;
import com.thanhtung.mp3downloader.async.DownloadAsync;
import com.thanhtung.mp3downloader.databinding.FragmentSongDetailBinding;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.model.SongDetail;
import com.thanhtung.mp3downloader.ui.online.OnlineMusicFragment;
import com.thanhtung.mp3downloader.ui.online.search.OnlineSearchMusicViewModel;

public class MusicDetailFragment extends Fragment implements View.OnClickListener {
    private FragmentSongDetailBinding binding;
    private SongDetailPagerAdapter adapter;
    private OnlineMusicFragment fmOnline;
    private MusicDetailViewModel musicDetailViewModel;
    private OnlineSearchMusicViewModel searchMusicViewModel;
    private DownloadAsync async;
    private Context context;
    private final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_detail, container, false);
        fmOnline = (OnlineMusicFragment) getParentFragment();
        adapter = new SongDetailPagerAdapter(getChildFragmentManager());
        binding.pager.setAdapter(adapter);
        binding.circleIndicator.setViewPager(binding.pager);
        binding.imvBack.setOnClickListener(this);

        context = container.getContext();

        getSongDetail();
        getItemSongSelected();
        return binding.getRoot();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imv_back:
                fmOnline.showFragment(fmOnline.getFmSearch(), android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.imv_download:
                if (checkPermission()){
                    async = new DownloadAsync(downloadCallback);
                    async.execute();
                }
                break;
        }

    }

    private void getItemSongSelected() {
        searchMusicViewModel = ViewModelProviders.of(requireActivity()).get(OnlineSearchMusicViewModel.class);
        searchMusicViewModel.getItemSongSelected().observe(getViewLifecycleOwner(), new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                binding.setItem(song);
                musicDetailViewModel.setSong(song);
            }
        });
    }
    private DownloadAsync.DownloadCallback downloadCallback = new DownloadAsync.DownloadCallback() {
        @Override
        public void onDownloadUpdate(int percent) {
            Log.e("TAG","PERCENT: "+percent);
        }

        @Override
        public void onDownloadSuccess(String path) {
            Log.e("TAG","DONE "+path);
        }
    };
    private void getSongDetail() {
        musicDetailViewModel = ViewModelProviders.of(requireActivity()).get(MusicDetailViewModel.class);
        musicDetailViewModel.getSongDetail().observe(getViewLifecycleOwner(), new Observer<SongDetail>() {
            @Override
            public void onChanged(final SongDetail songDetail) {
                Log.e("TAG","");
                binding.imvDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        async = new DownloadAsync(downloadCallback);
                        async.execute(songDetail.getSongDownloadLink());
                    }
                });

            }
        });
    }
    private boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : PERMISSIONS) {
                int check = context.checkSelfPermission(p);
                if (check != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }
}

package com.thanhtung.mp3downloader.ui.youtube;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.YoutubeConfig;
import com.thanhtung.mp3downloader.async.GetVideoAsync;
import com.thanhtung.mp3downloader.databinding.FragmentPlayVideoBinding;
import com.thanhtung.mp3downloader.model.youtubemodel.Item;

public class PlayVideoFragment extends Fragment implements YouTubePlayer.OnInitializedListener, View.OnClickListener {
    private FragmentPlayVideoBinding binding;
    private Item video;
    private YouTubePlayer youTubePlayer;
    private String baseUrl = "https://www.y2mate.com/youtube/";
    public void setVideo(Item video) {
        this.video = video;
        initVideo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_video, container, false);
        binding.lnDownloadMp3.setOnClickListener(this);
        binding.lnDownloadVideo.setOnClickListener(this);
        return binding.getRoot();
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setFullscreen(false);
        youTubePlayer.loadVideo(video.getId().videoId);
        youTubePlayer.setShowFullscreenButton(false);
        this.youTubePlayer = youTubePlayer;
        Log.e("TAG", "BOOLEAN: " + b);
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getContext(), "System Error", Toast.LENGTH_SHORT).show();
    }

    private void initVideo() {
        YouTubePlayerSupportFragment fragment = new YouTubePlayerSupportFragment();
        fragment.initialize(YoutubeConfig.getApiKey(), this);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_fragment, fragment);
        transaction.commit();
    }

    //
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("TAG", "ORIENTATION: " + newConfig.orientation);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ln_download_mp3:

                break;
            case R.id.ln_download_video:
                GetVideoAsync async = new GetVideoAsync();
                async.execute(baseUrl+video.getId().videoId);
                break;
        }
    }
}

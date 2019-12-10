package com.thanhtung.mp3downloader.ui.youtube;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.thanhtung.mp3downloader.databinding.FragmentPlayVideoBinding;
import com.thanhtung.mp3downloader.model.youtubemodel.Item;

public class PlayVideoFragment extends Fragment implements YouTubePlayer.OnInitializedListener {
    private FragmentPlayVideoBinding binding;
    private Item video;

    public void setVideo(Item video) {
        this.video = video;
        initVideo();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_video, container, false);
        return binding.getRoot();
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(video.getId().videoId);
        youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);

    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
    private void initVideo(){
        YouTubePlayerSupportFragment fragment = new YouTubePlayerSupportFragment();
        fragment.initialize(YoutubeConfig.getApiKey(), this);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_fragment, fragment);
        transaction.commit();
    }
}

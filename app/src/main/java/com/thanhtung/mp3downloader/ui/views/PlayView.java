package com.t3h.mp3music.views;

import android.annotation.TargetApi;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.t3h.mp3music.R;
import com.t3h.mp3music.databinding.PlayViewBinding;
import com.t3h.mp3music.service.MP3Service;

public class PlayView extends FrameLayout implements PlayViewListener {

    private PlayViewBinding binding;
    private MP3Service service;

    public PlayView(Context context) {
        super(context);
        init();
    }

    public PlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PlayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        binding = PlayViewBinding.inflate(
                LayoutInflater.from(getContext()),
                this,
                true
        );
        binding.setListener(this);
        setVisibility(GONE);
    }

    public void setService(MP3Service service) {
        this.service = service;
        AppCompatActivity act = (AppCompatActivity) getContext();
        service.getIsStarted().observe(act, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isStarted) {
                if (isStarted) {
                    setVisibility(VISIBLE);
                }else {
                    setVisibility(GONE);
                }
            }
        });
        service.getName().observe(act, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.setName(s);
            }
        });
        service.getIsPlaying().observe(act, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isPlaying) {
                if (isPlaying) {
                    binding.imPlay.setImageResource(R.drawable.ic_pause);
                }else {
                    binding.imPlay.setImageResource(R.drawable.ic_play);
                }
            }
        });
        service.getTime().observe(act, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer time) {
                binding.setTime(time);
            }
        });
    }

    @Override
    public void onNext() {
        service.change(MP3Service.NEXT);
    }

    @Override
    public void onPrev() {
        service.change(MP3Service.PREV);
    }

    @Override
    public void onPlay() {
        if (service.getIsPlaying().getValue()) {
            service.pause();
        }else {
            service.start();
        }
    }
}

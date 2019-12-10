package com.thanhtung.mp3downloader.ui.views;

import android.annotation.TargetApi;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.databinding.PlayViewBinding;
import com.thanhtung.mp3downloader.model.OfflineSong;
import com.thanhtung.mp3downloader.service.PlaySongService;


public class PlayView extends FrameLayout implements PlayViewListener, SeekBar.OnSeekBarChangeListener {

    private PlayViewBinding binding;
    private PlaySongService service;
    private int duration;

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
        binding.seekBar.setOnSeekBarChangeListener(this);
        setVisibility(GONE);
    }

    public void setService(PlaySongService service) {
        this.service = service;
        AppCompatActivity act = (AppCompatActivity) getContext();
        service.getIsStarted().observe(act, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isStarted) {
                if (isStarted != null) {
                    if (isStarted) {
                        setVisibility(VISIBLE);
                    } else {
                        setVisibility(GONE);
                    }
                }

            }
        });

        service.getOffLineSong().observe(act, new Observer<OfflineSong>() {
            @Override
            public void onChanged(OfflineSong offlineSong) {
                Log.e("TAG", "OFF-SOng: " + offlineSong.getArtist());
                binding.setArtist(offlineSong.getArtist());
                binding.setName(offlineSong.getTitle());
                duration = offlineSong.getDuration();
                binding.setTotalTime(offlineSong.getDuration());
            }
        });

        service.getTime().observe(act, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.setTime(integer);
                binding.seekBar.setMax(10000);
                binding.seekBar.setProgress(integer * 10000 / duration);
//                Log.e("TAG","TIME: "+integer +"----- Total: "+duration);
//                Log.e("TAG","Progress"+(integer*100/duration));
            }
        });

        service.getIsPlaying().observe(act, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isPlaying) {
                if (isPlaying != null) {
                    if (isPlaying) {
                        binding.imPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    } else {
                        binding.imPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    }
                }

            }
        });

    }

    @Override
    public void onNext() {
        service.change(PlaySongService.NEXT);
    }


    @Override
    public void onPrev() {
        service.change(PlaySongService.PREV);
    }

    @Override
    public void onPlay() {
        if (service.getIsPlaying().getValue() != null) {
            if (service.getIsPlaying().getValue()) {
                service.pause();
            } else {
                service.start();
            }
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            service.seek(progress / 1000);
            seekBar.setProgress(progress);
            Log.e("TAG", "progress: " + progress / 10000);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}

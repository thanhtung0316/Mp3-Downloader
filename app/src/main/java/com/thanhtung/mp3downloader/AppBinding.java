package com.thanhtung.mp3downloader;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;

public class AppBinding {
    private static SimpleDateFormat format
            = new SimpleDateFormat("mm:ss");

    @BindingAdapter("time")
    public static void setTime(TextView tv, int duration) {
        String time = format.format(duration);
        tv.setText(time);
    }

    @BindingAdapter("thumb")
    public static void setThumb(ImageView im, String img) {
        Glide.with(im)
                .load(img)
                .into(im);
    }
}

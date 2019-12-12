package com.thanhtung.mp3downloader.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.thanhtung.mp3downloader.BaseFragment;

public class SongPagerAdapter extends FragmentPagerAdapter {
    private BaseFragment[] fms;
    private String TAG = "SongPagerAdapter";

    public SongPagerAdapter(@NonNull FragmentManager fm, int behavior, BaseFragment... fms) {
        super(fm, behavior);
        this.fms = fms;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fms[position];
    }

    @Override
    public int getCount() {
        return fms == null ? 0 : fms.length;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (fms[position] != null) {
            return fms[position].getTitle();
        } else {
            return "";
        }

    }
}

package com.thanhtung.mp3downloader.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.thanhtung.mp3downloader.ui.online.rank.KoreaFragment;
import com.thanhtung.mp3downloader.ui.online.rank.UsUkFragment;
import com.thanhtung.mp3downloader.ui.online.rank.VietNamFragment;

public class RankingPagerAdapter extends FragmentStatePagerAdapter {
    private VietNamFragment fmVietnam = new VietNamFragment();
    private UsUkFragment fmUsUk = new UsUkFragment();
    private KoreaFragment fmKorea = new KoreaFragment();

    private String[] listTab={"Viet Nam","US UK","Korea"};

    public RankingPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return fmVietnam;
            case 1:
                return fmUsUk;
            case 2:
                return fmKorea;
        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}

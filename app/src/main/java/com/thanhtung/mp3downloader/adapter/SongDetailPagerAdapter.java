package com.thanhtung.mp3downloader.adapter;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.thanhtung.mp3downloader.ui.online.detail.LyricFragment;
import com.thanhtung.mp3downloader.ui.online.detail.PlaySongFragment;

public class SongDetailPagerAdapter extends FragmentPagerAdapter {
    private Fragment mCurrentFragment;

    public SongDetailPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PlaySongFragment();
                break;
            case 1:
                fragment = new LyricFragment();
                break;
        }
        return fragment;
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (getCurrentFragment() != object) {
            mCurrentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public int getCount() {
        return 2;
    }

}

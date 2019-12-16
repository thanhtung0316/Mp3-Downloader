package com.thanhtung.mp3downloader.ui.online.rank;

import com.thanhtung.mp3downloader.BaseFragment;
import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.databinding.FragmentUsukRankingMusicBinding;
import com.thanhtung.mp3downloader.databinding.FragmentVietnamRankingMusicBinding;

public class USUKRankingFragment extends BaseFragment<FragmentUsukRankingMusicBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_usuk_ranking_music;
    }

    @Override
    public String getTitle() {
        return "US UK" ;
    }
}

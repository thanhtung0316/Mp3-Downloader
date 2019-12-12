package com.thanhtung.mp3downloader.ui.online.rank.korea;

import com.thanhtung.mp3downloader.BaseFragment;
import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.databinding.FragmentVietnamRankingMusicBinding;

public class KoreaRankingMusicFragment extends BaseFragment<FragmentVietnamRankingMusicBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vietnam_ranking_music;
    }

    @Override
    public String getTitle() {
        return "Korea";
    }
}

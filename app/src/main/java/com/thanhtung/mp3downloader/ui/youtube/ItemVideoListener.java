package com.thanhtung.mp3downloader.ui.youtube;

import com.thanhtung.mp3downloader.adapter.BaseAdapter;
import com.thanhtung.mp3downloader.model.youtubemodel.Item;

public interface ItemVideoListener extends BaseAdapter.BaseItemlistener {
    void onVideoClicked(Item video);
}

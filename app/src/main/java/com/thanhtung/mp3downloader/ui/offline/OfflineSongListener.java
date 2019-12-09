package com.thanhtung.mp3downloader.ui.offline;

import com.thanhtung.mp3downloader.adapter.BaseAdapter;
import com.thanhtung.mp3downloader.model.OfflineSong;

public interface OfflineSongListener extends BaseAdapter.BaseItemlistener {
    void onSongClicked(OfflineSong offlineSong);
}

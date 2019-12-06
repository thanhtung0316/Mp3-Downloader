package com.thanhtung.mp3downloader.ui.offline;

import com.thanhtung.mp3downloader.adapter.BaseSongAdapter;
import com.thanhtung.mp3downloader.model.OfflineSong;

public interface OfflineSongListener extends BaseSongAdapter.BaseItemlistener {
    void onSongClicked(OfflineSong offlineSong);
}

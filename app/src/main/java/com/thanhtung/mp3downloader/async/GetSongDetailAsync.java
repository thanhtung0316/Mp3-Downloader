package com.thanhtung.mp3downloader.async;

import android.os.AsyncTask;
import android.util.Log;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.model.SongDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetSongDetailAsync extends AsyncTask<Song, Void, SongDetail> {
    private SongDetail songDetail;
    private GetSongDetailCallBack mCallback;

    public void setmCallback(GetSongDetailCallBack mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    protected void onPreExecute() {
        songDetail = new SongDetail();
        super.onPreExecute();
    }

    @Override
    protected SongDetail doInBackground(Song... songs) {
        Song song = songs[0];
        try {
            if (song!= null) {
                String url = song.getLinkDetail();
                Document doc = Jsoup.connect(url).get();
                String songLyric = doc.getElementById("fulllyric").html().replace("<br>","");
                Elements eSong = doc.select("body > section > div.container > div > div.col-md-9 " +
                        "> div:nth-child(4) > div.col-md-4 > div > div.card-body > ul");
                String songComposer = eSong.tagName("span").text();


                Elements downloadInfo = doc.getElementById("pills-download")
                        .select("#pills-download > div > div.card-body > div > div.col-12.tab_download_music > ul > li:nth-child(1)");

                for (Element e:downloadInfo  ) {
                    Log.e("TAG","E: "+downloadInfo.size());
                }

                songDetail.setSongName(song.getSongName());
                songDetail.setSongArtist(song.getSongArtist());
                songDetail.setImageLink(song.getImageLink());
                songDetail.setSongQuality(song.getSongQuality());
                songDetail.setLinkDetail(song.getLinkDetail());
                songDetail.setSongViews(song.getSongViews());


                songDetail.setSongLyric(songLyric);
                songDetail.setSongComposer(songComposer);

//                Log.e("TAG","Download: "+downloadInfo);

            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LOG", "ERROR: " + e);
        }

        return songDetail;
    }


    @Override
    protected void onPostExecute(SongDetail songDetail) {
        super.onPostExecute(songDetail);
        mCallback.OnGetSongDetailComplete(songDetail);
    }

    public interface GetSongDetailCallBack {
        void OnGetSongDetailComplete(SongDetail songDetail);
    }
}

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
                Elements eSong = doc.select("body > section > div.container > div > div.col-md-9 > div:nth-child(4) > div.col-md-4 > div > div.card-body > ul");
                String songComposer = eSong.tagName("span").text();
                Elements downloadInfo = doc.getElementById("pills-download").select("#pills-download > div > div.card-body > div > div.col-12.tab_download_music");
                for (Element e: downloadInfo) {
                    String x = e.attr("href");
                    Log.e("TAG","downloadInfo: "+x);
                }




            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LOG", "ERROR: " + e);
        }

        return null;
    }


    @Override
    protected void onPostExecute(SongDetail songDetail) {
        super.onPostExecute(songDetail);
    }
}

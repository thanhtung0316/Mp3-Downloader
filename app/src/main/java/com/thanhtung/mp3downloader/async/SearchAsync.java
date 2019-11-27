package com.thanhtung.mp3downloader.async;

import android.os.AsyncTask;
import android.util.Log;

import com.thanhtung.mp3downloader.model.Song;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
public class SearchAsync extends AsyncTask<String, Void, ArrayList<Song>> {
    private ArrayList<Song> songs;
    private SearchTaskCallBack mCallback;

    public void setCallback(SearchTaskCallBack mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    protected ArrayList<Song> doInBackground(String... strings) {
        songs = new ArrayList<>();
        try {
            String url = strings[0];
            if (url!=null){
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.select("body > section > div.container > div > div.col-md-9 > div.tab-content > #nav-music > ul > li");
                for (Element el : elements) {
                    String linkDetail = el.select("div > a").attr("href");
                    String songName = el.select("div > a").attr("title");
                    String songArtist = el.select("div > div > div").text();
                    String imgLink = el.select("div > a > img").attr("src");
                    String songQuality = el.select("div > small > span.card-text").text();
                    String viewCount = el.select("div > small.time_stt").text();
                    Song song = new Song();
                    song.setLinkDetail(linkDetail);
                    song.setSongName(songName);
                    song.setSongArtist(songArtist);
                    song.setImageLink(imgLink);
                    song.setSongQuality(songQuality);
                    song.setSongViews(viewCount);
                    songs.add(song);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LOG", "SEARCH ERROR" + e);
        }

        return songs;
    }

    @Override
    protected void onPostExecute(ArrayList<Song> songs) {
        super.onPostExecute(songs);
        mCallback.OnSearchTaskComplete(songs);
    }

    public interface SearchTaskCallBack {
        void OnSearchTaskComplete(ArrayList<Song> songs);
    }

}

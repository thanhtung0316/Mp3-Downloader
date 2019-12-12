package com.thanhtung.mp3downloader.async;

import android.os.AsyncTask;
import android.util.Log;

import com.thanhtung.mp3downloader.model.Song;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class OnlineMusicAsync extends AsyncTask<String, Void, ArrayList<Song>> {
    private ArrayList<Song> songs;
    private String TAG = "OnlineMusicAsync";
    private Song song;
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
                int rank = 0;
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.getElementById("home").select("ul > li");
                for (Element el : elements) {
                    song = new Song();
                    ++rank;
                    String songName = el.select("li > div.media-left.mr-3 > a").attr("title");
                    String linkDetail = el.select("li > div.media-left.mr-3 > a").attr("href");
//
                    String songArtist = el.getElementsByClass("author").select("a").text();
                    String imgLink = el.select("a > img").attr("src");
//
                    String viewCount = el.select("li > div > div > small").text();
                    song.setSongRank(rank);
                    song.setLinkDetail(linkDetail);
                    song.setSongName(songName);
                    song.setSongArtist(songArtist);
                    song.setImageLink(imgLink);

                    song.setSongViews(viewCount);
                    songs.add(song);
//                    Log.e("ONLINE MUSIC ASYNC","NAME: "+songName+"==IMG: "+imgLink+"  ==AUTHOR: "+songArtist+" == Views: "+viewCount+" RANK: "+rank);
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
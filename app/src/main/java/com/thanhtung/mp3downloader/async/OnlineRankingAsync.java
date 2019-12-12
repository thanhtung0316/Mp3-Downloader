package com.thanhtung.mp3downloader.async;

import android.os.AsyncTask;
import android.util.Log;

import com.thanhtung.mp3downloader.model.Song;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OnlineRankingAsync extends AsyncTask<String, Void, List<List<Song>>> {

    private Song song;
    private List<List<Song>> lists;
    private OnRankingSongCallback mCallback;
    private Document doc;


    public void setmCallback(OnRankingSongCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    protected List<List<Song>> doInBackground(String... strings) {
        lists = new ArrayList<>();
        try {
            String url = strings[0];
            if (url != null) {
                doc = Jsoup.connect(url).get();
                getListRanking("home");
                getListRanking("profile");
                getListRanking("contact");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    protected void onPostExecute(List<List<Song>> lists) {
        super.onPostExecute(lists);
        mCallback.onGetRankingComplete(lists);
        Log.e("LOG", "SEARCH OK: " + lists);
    }


    private void getListRanking(String id) {
        List<Song> songs = new ArrayList<>();
        int rank = 0;
        Elements ranking = doc.getElementById(id).select("ul > li");
        for (Element el : ranking) {
            song = new Song();
            ++rank;
            String songName = el.select("li > div.media-left.mr-3 > a").attr("title");
            String linkDetail = el.select("li > div.media-left.mr-3 > a").attr("href");
            String songArtist = el.getElementsByClass("author").select("a").text();
            String imgLink = el.select("a > img").attr("src");
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
        lists.add(songs);
    }
    public interface OnRankingSongCallback{
        void onGetRankingComplete(List<List<Song>> lists);
    }
}

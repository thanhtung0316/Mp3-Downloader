package com.thanhtung.mp3downloader.async;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GetVideoAsync extends AsyncTask<String, Integer, String> {


    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        try {
            Log.e("TAG","URL: "+url);
            Document doc = Jsoup.connect(url).get();
            String mp3DownloadLink = doc.nodeName();
            Log.e("TAG", "Download Link: " + mp3DownloadLink);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("TAG","ON POST: "+s);
    }
}

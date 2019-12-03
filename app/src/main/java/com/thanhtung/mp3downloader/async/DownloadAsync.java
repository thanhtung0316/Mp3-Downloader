package com.thanhtung.mp3downloader.async;


import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAsync extends AsyncTask<String, Integer, String> {

    private DownloadCallback callback;
    private File file;
    public DownloadAsync(DownloadCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        int count;
        try {
            URL url = new URL(strings[0]);
            URLConnection connection = url.openConnection();
            connection.connect();
            int lengthOfFile = connection.getContentLength();
            Log.e("TAG", "LENGTH: " + lengthOfFile);
            InputStream inputStream = connection.getInputStream();

            file = new File("sdcard/");

            OutputStream outputStream = new FileOutputStream(file);

            byte[] data = new byte[1024];
            long total = 0;

            while ((count = inputStream.read(data)) != -1) {
                total += count;
                publishProgress((int) (total * 100) / lengthOfFile);
                outputStream.write(data, 0, count);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        callback.onDownloadUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callback.onDownloadSuccess(s);
        String absolutePath = file.getAbsolutePath();
        Log.e("TAG","PATH: "+absolutePath);
    }


    public interface DownloadCallback {
        void onDownloadUpdate(int percent);
        void onDownloadSuccess(String path);
    }
}

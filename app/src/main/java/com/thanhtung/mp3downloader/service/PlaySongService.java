package com.thanhtung.mp3downloader.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.model.OfflineSong;

import java.util.List;

public class PlaySongService extends Service implements MediaPlayer.OnCompletionListener {
    private final String ACTION_NEXT = "action.NEXT";
    private final String ACTION_PREV = "action.PREV";
    private final String ACTION_PLAY = "action.PLAY";
    private final String ACTION_EXIT = "action.EXIT";

    private MediaPlayer player;
    private List<OfflineSong> data;
    private int currentIndex = -1;

    private MutableLiveData<Integer> time = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<Boolean> isPlaying = new MutableLiveData<>();
    private MutableLiveData<Boolean> isStarted = new MutableLiveData<>();
    private MutableLiveData<OfflineSong> offLineSong = new MutableLiveData<>();

    private boolean isRunning = false;

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_PLAY);
        filter.addAction(ACTION_PREV);
        filter.addAction(ACTION_NEXT);
        filter.addAction(ACTION_EXIT);
        registerReceiver(receiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (isRunning == false) {
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        time.postValue(currentPosition());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        return START_NOT_STICKY;

    }

    public void pushNotification(String name, String artist) {
        Intent intent = new Intent(this, getClass());
        startService(intent);
        String channelId = "PlaySongService";

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.mp3_notification);
        remoteViews.setTextViewText(R.id.tv_title, name+" - "+artist);

        setAction(remoteViews, R.id.im_next, ACTION_NEXT);
        setAction(remoteViews, R.id.im_prev, ACTION_PREV);
        setAction(remoteViews, R.id.im_play, ACTION_PLAY);
        setAction(remoteViews, R.id.im_close, ACTION_EXIT);

        if (player.isPlaying()) {
            remoteViews.setImageViewResource(R.id.im_play, R.drawable.ic_pause_circle_outline_black_24dp);
        } else {
            remoteViews.setImageViewResource(R.id.im_play, R.drawable.ic_play_circle_outline_black_24dp);
        }
        builder.setCustomBigContentView(remoteViews);
        startForeground(1221233, builder.build());

    }

    public void setAction(RemoteViews remoteViews, int id, String action) {
        Intent intent = new Intent(action);
        PendingIntent pending = PendingIntent.getBroadcast(this, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(id, pending);
    }


    public void create(int index) {
        name.postValue(data.get(index).getTitle());
        offLineSong.postValue(data.get(index));
        isStarted.postValue(true);

        currentIndex = index;
        release();
        Uri uri = Uri.parse(data.get(index).getData());
        player = MediaPlayer.create(this, uri);
        start();
        player.setOnCompletionListener(this);
    }

    public void release() {
        if (player != null) {
            player.release();
        }
    }

    public void start() {
        if (player != null) {
            isPlaying.postValue(true);
            player.start();
            pushNotification(data.get(currentIndex).getTitle(), data.get(currentIndex).getArtist());
        }
    }

    public void pause() {
        if (player != null) {
            isPlaying.postValue(false);
            player.pause();
            pushNotification(data.get(currentIndex).getTitle(), data.get(currentIndex).getArtist());
        }
    }

    public int getDuration() {
        if (player != null) {
            return player.getDuration();
        }
        return 0;
    }

    public int currentPosition() {
        if (player != null) {
            return player.getCurrentPosition();
        }
        return 0;
    }

    public void seek(int position) {
        if (player != null) {
            player.seekTo(position);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MP3Binder(this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        change(NEXT);
    }

    public class MP3Binder extends Binder {
        private PlaySongService service;

        public MP3Binder(PlaySongService service) {
            this.service = service;
        }

        public PlaySongService getService() {
            return service;
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                switch (intent.getAction()) {
                    case ACTION_NEXT:
                        change(NEXT);
                        break;
                    case ACTION_PLAY:
                        if (player.isPlaying()) {
                            pause();
                        } else {
                            start();
                        }
                        break;
                    case ACTION_PREV:
                        change(PREV);
                        break;
                    case ACTION_EXIT:
                        isStarted.postValue(false);
                        isRunning = false;
                        stopForeground(true);
                        stopSelf();
                        release();
                        break;
                }
            }
        }
    };

    public static final int NEXT = 1;
    public static final int PREV = -1;

    @IntDef({NEXT, PREV})
    public @interface Change {
    }



    public void change(@Change int value) {
        currentIndex += value;
        if (currentIndex >= data.size()) {
            currentIndex = 0;
        } else if (currentIndex < 0) {
            currentIndex = data.size() - 1;
        }
        create(currentIndex);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        player.release();
    }

    public void setData(List<OfflineSong> data) {
        this.data = data;
    }

    public List<OfflineSong> getData() {
        return data;
    }
    public MutableLiveData<Integer> getTime() {
        return time;
    }

    public void setTime(MutableLiveData<Integer> time) {
        this.time = time;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public MutableLiveData<Boolean> getIsPlaying() {
        return isPlaying;
    }


    public MutableLiveData<Boolean> getIsStarted() {
        return isStarted;
    }

    public MutableLiveData<OfflineSong> getOffLineSong() {
        return offLineSong;
    }
}

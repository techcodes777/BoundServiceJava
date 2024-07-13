package com.eclatsol.boundservicejava.service;

import static com.eclatsol.boundservicejava.MainActivity.tvTime;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    ScheduledExecutorService scheduledExecutorService;
    IBinder iBinder = new Binder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    tvTime.setText("Current Time: " + Instant.now());
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        scheduledExecutorService.shutdown();
        return super.onUnbind(intent);
    }
}

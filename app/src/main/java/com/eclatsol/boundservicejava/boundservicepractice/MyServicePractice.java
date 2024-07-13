package com.eclatsol.boundservicejava.boundservicepractice;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyServicePractice extends Service {

    ScheduledExecutorService scheduledExecutorService;
    IBinder binder = new Binder();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("BoundService", "onCreate: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("BoundService", "onBind: ");
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                BoundActivity.tvCurrentTime.setText("Time" + new Date());
            }
        },1,1, TimeUnit.SECONDS);

        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("BoundService", "onUnbind: ");
        scheduledExecutorService.shutdown();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("BoundService", "onDestroy: ");

    }

    public static void task1() {
        System.out.println("Running task1...");
    }

    public static void task3() {
        System.out.println("Running task3...");
    }
}

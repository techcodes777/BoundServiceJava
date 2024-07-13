package com.eclatsol.boundservicejava.boundservicepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eclatsol.boundservicejava.R;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BoundActivity extends AppCompatActivity {


    static TextView tvCurrentTime;
    Button btnStartService, btnStopService;
    boolean bound = false;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);

        tvCurrentTime = findViewById(R.id.tvCurrentTime);
        btnStartService = findViewById(R.id.btnStartService);
        btnStopService = findViewById(R.id.btnStopService);

            tvCurrentTime.setText("Time" + new Date());

//        scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                tvCurrentTime.setText("Time" + new Date());
//            }
//        },1,1, TimeUnit.SECONDS);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(new Intent(BoundActivity.this, MyServicePractice.class), serviceConnection, BIND_AUTO_CREATE);
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bound) {
                    unbindService(serviceConnection);
                    bound = false;
                }

            }
        });


    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("BoundService", "onServiceConnected: ");
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("BoundService", "onServiceDisconnected: ");
            bound = false;
        }
    };
}
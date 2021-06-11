package com.liepu.fluttermix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

public class NotificationActivity extends AppCompatActivity {

    RecyclerService ss = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        bindService(new Intent(this, RecyclerService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Toast.makeText(NotificationActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                ss = ((RecyclerService.MyBinder)service).getService();

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

        ((Button)findViewById(R.id.btn_service)).setOnClickListener(v -> Toast.makeText(this, ss.getString(), Toast.LENGTH_SHORT));
    }
}
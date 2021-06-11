package com.liepu.fluttermix;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Trace;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.liepu.lib_annotation.LiveData;

import java.util.ArrayList;
import java.util.List;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.btn_flutter);
        button.setOnClickListener(v -> {
//            Snackbar.make(button, "show flutter", Snackbar.LENGTH_SHORT).show();
//            startActivity(
//                    FlutterWrapperActivity
//                            .withCachedEngine(MixApplication.ENGINE_ID)
////                            .withNewEngine()
//                            .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
//                            .build(this)
//            );
            startActivity(new Intent(this, FlutterWrapperActivity.class));
        });
        MyViewModel myViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel.class);
        myViewModel.nameLiveData.observe(this, s -> {
            Toast.makeText(MainActivity.this, s+"0", Toast.LENGTH_SHORT).show();
        });
        myViewModel.nameLiveData.observe(this, s ->
                Toast.makeText(MainActivity.this, s+"1", Toast.LENGTH_SHORT).show());

        myViewModel.nameLiveData.setValue("3");
        // 启动后台服务
        startService(new Intent(this, NotificationService.class));

        // 发送广播
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("111", "normal");
                Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        };
        IntentFilter filter = new IntentFilter("111");
        registerReceiver(receiver, filter);

        BroadcastReceiver localReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("111", "local");
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(localReceiver, filter);


        BroadcastReceiver order1 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("111", "order1");
            }
        };
        IntentFilter order1Filter = new IntentFilter("111");
        order1Filter.setPriority(1);
        registerReceiver(order1, order1Filter);

        BroadcastReceiver order2 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("111", "order2");
            }
        };
        IntentFilter order2Filter = new IntentFilter("111");
        order2Filter.setPriority(2);
        registerReceiver(order2, order2Filter);
        BroadcastReceiver order3 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("111", "order3");
            }
        };
        IntentFilter order3Filter = new IntentFilter("111");
        order3Filter.setPriority(3);
        registerReceiver(order3, order3Filter);


        Button btnReceiver = findViewById(R.id.btn_receiver);
        btnReceiver.setOnClickListener(v -> {
            // 普通广播
            Intent intent = new Intent();
            intent.setAction("111");
            sendBroadcast(intent);
            // 本地广播
            LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
            // 静态广播
            Intent staticIntent = new Intent();
            staticIntent.setAction("111");
            staticIntent.setComponent(new ComponentName(MainActivity.this, StaticBroadcastReceiver.class));
            sendBroadcast(staticIntent);

            // 有序广播
            sendOrderedBroadcast(intent, null, order3, null, Activity.RESULT_OK, "", null);

            myViewModel.nameLiveData.setValue("5");
        });

        Button btnScroll = findViewById(R.id.btn_scroll);
        btnScroll.setOnClickListener(v -> {
            btnScroll.scrollTo(10, 10);
            int len = btnScroll.getLeft() + 100;
            ObjectAnimator.ofInt(btnScroll, "left", len).start();
            ObjectAnimator.ofInt(btnScroll, "right", btnScroll.getRight() + 100).start();
            startActivity(new Intent(this, RecyclerActivity.class));
        });


        List<Man> manList = new ArrayList<>();
        List<Woman> womenList = new ArrayList<>();
        List<Body> bodyList = new ArrayList<>();
        processExtend(manList);

        processSuper(bodyList);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void processExtend(List<? extends Human> list) {
//        list.get(0).testHuman();
    }
    public void processSuper(List<? super Human> list) {
        list.add(new Woman());
    }


    @LiveData
    public void test() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public static class Body {
        public void testBody() {}
    }
    public static class Human extends Body {
        public void testHuman() {}
    }
    public static class Woman extends Human {}
    public static class Man extends Human {}
}
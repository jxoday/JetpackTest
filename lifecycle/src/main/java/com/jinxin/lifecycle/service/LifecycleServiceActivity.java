package com.jinxin.lifecycle.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jinxin.lifecycle.R;


/**
 * LifecycleService的具体使用方法
 * @author JinXin
 */
public class LifecycleServiceActivity extends AppCompatActivity {

    private static final String TAG = "LifecycleServiceActivit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_service);

        findViewById(R.id.btn_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动服务
                Intent intent = new Intent(LifecycleServiceActivity.this, MyService.class);
                startService(intent);
            }
        });

        findViewById(R.id.btn_stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 停止服务
                Intent intent = new Intent(LifecycleServiceActivity.this, MyService.class);
                stopService(intent);
            }
        });
    }
}
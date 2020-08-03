package com.jinxin.jetpacktest.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jinxin.jetpacktest.R;
import com.jinxin.jetpacktest.lifecycle.activity.LifeCycleActivity;
import com.jinxin.jetpacktest.lifecycle.service.LifecycleServiceActivity;

/**
 * @author JinXin
 */
public class LifecycleMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_main);

        // Lifecycle组件存在的主要意义时帮助我们解耦，让自定义组件也能够感受到生命周期的变化。
        findViewById(R.id.btn_life_cycle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LifecycleMainActivity.this, LifeCycleActivity.class));
            }
        });

        findViewById(R.id.btn_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LifecycleMainActivity.this, LifecycleServiceActivity.class));
            }
        });
    }
}
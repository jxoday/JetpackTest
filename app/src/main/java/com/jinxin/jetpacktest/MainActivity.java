package com.jinxin.jetpacktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jinxin.jetpacktest.lifecycle.LifecycleMainActivity;
import com.jinxin.jetpacktest.lifecycle.activity.LifeCycleActivity;
import com.jinxin.jetpacktest.livedata.LiveDataTestActivity;
import com.jinxin.jetpacktest.navigation.NavigationActivity;
import com.jinxin.jetpacktest.viewmodel.ViewModelTestActivity;

/**
 * @author JinXin
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_life_cycle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LifecycleMainActivity.class));
            }
        });

        findViewById(R.id.btn_navigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NavigationActivity.class));
            }
        });

        findViewById(R.id.btn_view_model).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewModelTestActivity.class));
            }
        });

        findViewById(R.id.btn_live_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LiveDataTestActivity.class));
            }
        });
    }
}
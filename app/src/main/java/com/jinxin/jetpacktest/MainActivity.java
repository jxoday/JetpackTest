package com.jinxin.jetpacktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jinxin.jetpacktest.databinding.DataBindingTestActivity;
import com.jinxin.jetpacktest.databinding.RecyclerViewBindingActivity;
import com.jinxin.jetpacktest.databinding.TowWayBindingActivity;
import com.jinxin.jetpacktest.lifecycle.LifecycleMainActivity;
import com.jinxin.jetpacktest.livedata.LiveDataFragmentActivity;
import com.jinxin.jetpacktest.livedata.LiveDataTestActivity;
import com.jinxin.jetpacktest.navigation.NavigationActivity;
import com.jinxin.jetpacktest.room.RoomTestActivity;
import com.jinxin.jetpacktest.room.livedata.LiveDataRoomTestActivity;
import com.jinxin.jetpacktest.viewmodel.ViewModelTestActivity;
import com.jinxin.jetpacktest.workmanager.WorkManagerTestActivity;
import com.jinxin.paging.positionaldatasource.PagingMainActivity;

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

        findViewById(R.id.btn_live_data_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LiveDataFragmentActivity.class));
            }
        });

        findViewById(R.id.btn_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RoomTestActivity.class));
            }
        });

        findViewById(R.id.btn_live_data_view_model_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LiveDataRoomTestActivity.class));
            }
        });

        findViewById(R.id.btn_work_manager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WorkManagerTestActivity.class));
            }
        });

        findViewById(R.id.btn_data_binding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataBindingTestActivity.class));
            }
        });

        findViewById(R.id.btn_two_way_binding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TowWayBindingActivity.class));
            }
        });

        findViewById(R.id.btn_recycle_view_binding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerViewBindingActivity.class));
            }
        });

        findViewById(R.id.btn_paging).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PagingMainActivity.class));
            }
        });

    }
}
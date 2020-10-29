package com.jinxin.jetpacktest;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jinxin.jetpacktest.databinding.DataBindingTestActivity;
import com.jinxin.jetpacktest.databinding.RecyclerViewBindingActivity;
import com.jinxin.jetpacktest.databinding.TowWayBindingActivity;
import com.jinxin.jetpacktest.livedata.LiveDataFragmentActivity;
import com.jinxin.jetpacktest.livedata.LiveDataTestActivity;
import com.jinxin.jetpacktest.room.RoomTestActivity;
import com.jinxin.jetpacktest.room.livedata.LiveDataRoomTestActivity;
import com.jinxin.jetpacktest.viewmodel.ViewModelTestActivity;
import com.jinxin.jetpacktest.workmanager.WorkManagerTestActivity;
import com.jinxin.lifecycle.LifecycleMainActivity;
import com.jinxin.navigation.NavigationActivity;
import com.jinxin.navigation.test.TestActivity;
import com.jinxin.paging.boundarycallback.BoundaryActivity;
import com.jinxin.paging.itemkeyeddatasrource.ItemKeyedMainActivity;
import com.jinxin.paging.pagekeyeddatasource.PageKeyedActivity;
import com.jinxin.paging.positionaldatasource.PagingMainActivity;

/**
 * @author JinXin
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_life_cycle).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LifecycleMainActivity.class)));

        findViewById(R.id.btn_navigation).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NavigationActivity.class)));

        findViewById(R.id.btn_navigation_ui).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity.class)));

        findViewById(R.id.btn_view_model).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewModelTestActivity.class)));

        findViewById(R.id.btn_live_data).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LiveDataTestActivity.class)));

        findViewById(R.id.btn_live_data_fragment).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LiveDataFragmentActivity.class)));

        findViewById(R.id.btn_room).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RoomTestActivity.class)));

        findViewById(R.id.btn_live_data_view_model_room).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LiveDataRoomTestActivity.class)));

        findViewById(R.id.btn_work_manager).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WorkManagerTestActivity.class)));

        findViewById(R.id.btn_data_binding).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DataBindingTestActivity.class)));

        findViewById(R.id.btn_two_way_binding).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TowWayBindingActivity.class)));

        findViewById(R.id.btn_recycle_view_binding).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RecyclerViewBindingActivity.class)));

        findViewById(R.id.btn_paging_positional).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PagingMainActivity.class)));

        findViewById(R.id.btn_paging_page_keyed).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PageKeyedActivity.class)));

        findViewById(R.id.btn_paging_item_keyed).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ItemKeyedMainActivity.class)));

        findViewById(R.id.btn_paging_boundary_callback).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BoundaryActivity.class)));

        findViewById(R.id.btn_mvvm).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, com.jinxin.mvvm.MainActivity.class)));

    }
}
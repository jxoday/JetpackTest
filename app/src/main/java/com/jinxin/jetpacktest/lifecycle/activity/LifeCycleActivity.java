package com.jinxin.jetpacktest.lifecycle.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.jinxin.jetpacktest.R;

/**
 * LifeCycle
 * 可以帮助开发者创建可感知生命周期的组件，这样能够再其内部管理自己的生命周期，
 * 从而降低模块间的耦合度，并降低内存泄漏发生的可能性
 *
 * LifeCycle实现原理
 * jetpack为提供了两个类：LifecycleOwner(被观察者)和LifeCycleObserver(观察者)
 * 即通过观察者模式，实现对页面生命周期的监听
 *
 * @author JinXin
 */
public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycleActivity";

    private MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        // 获取位置信息写法一： 初始化位置管理器
//        initLocationManager();

        // 获取位置信息写法二：使用Lifecycle感知生命周期
        // 自定义组件MyLocationListener，该组件实现LifeCycleObserver接口。
        // 获取地理位置相关的代码在该组件中完成
        myLocationListener = new MyLocationListener(this, new MyLocationListener.OnLocationChangedListener() {
            @Override
            public void onChanged(double latitude, double longitude) {

                // 展示收到的位置信息
                Log.d(TAG, "onChanged: ");
            }
        });

        // 获取位置信息写法二
        // 将观察者与被观察者绑定
        getLifecycle().addObserver(myLocationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 获取位置信息写法一： 开始获取用户的地理位置
//        startGetLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 获取位置信息写法一： 停止获取用户的地理位置
//        stopGetLocation();
    }

    private void initLocationManager() {

    }

    private void startGetLocation() {

    }

    private void stopGetLocation() {

    }
}
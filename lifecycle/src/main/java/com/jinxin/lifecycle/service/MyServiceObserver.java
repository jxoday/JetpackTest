package com.jinxin.lifecycle.service;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author JinXin 2020/7/27
 */
public class MyServiceObserver implements LifecycleObserver {

    private static final String TAG = "MyServiceObserver";

    /**
     * 当Service的onCreate()方法被调用时，该方法会被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void startGetLocation() {
        Log.d(TAG, "startGetLocation: 开始获取地理位置");
    }

    /**
     *  当Service的onDestroy()方法被调用时，该方法会被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void stopGetLocation() {
        Log.d(TAG, "stopGetLocation: 停止获取地理位置");
    }
}

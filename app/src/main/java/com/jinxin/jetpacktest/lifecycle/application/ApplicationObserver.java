package com.jinxin.jetpacktest.lifecycle.application;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 负责对应用程序生命周期的监听
 * @author JinXin 2020/7/27
 */
class ApplicationObserver implements LifecycleObserver {

    private static final String TAG = "ApplicationObserver";

    /**
     * 在应用程序的整个生命周期中只会被调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void onCreate() {
        Log.d(TAG, "onCreate: ");
    }

    /**
     * 当应用程序在前台出现时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart() {
        Log.d(TAG, "onStart: ");
    }

    /**
     * 当应用程序在前台出现时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        Log.d(TAG, "onResume: ");
    }

    /**
     * 当应用程序退出到后台时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {
        Log.d(TAG, "onPause: ");
    }

    /**
     * 当应用程序退出到后台时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop() {
        Log.d(TAG, "onStop: ");
    }

    /**
     * 永远不会被调用，系统不会分发调用ON_DESTROY
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        Log.d(TAG, "onDestroy: ");
    }
    
}

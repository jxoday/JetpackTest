package com.jinxin.lifecycle.application;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 负责对应用程序生命周期的监听
 *
 * Lifecycle.Event.ON_CREATE 只会被调用一次，而Lifecycle.Event.ON_DESTROY永远不会被调用
 * 当应用程序从后台回到前台，或者应用程序被首次打开时，会依次调用Lifecycle.Event.ON_START和Lifecycle.Event.ON_RESUME
 * 当应用程序从前台回到后台（用户按下Home键或任务菜单键），会依次调用Lifecycle.Event.ON_PAUSE和Lifecycle.Event.ON_STOP
 *
 * 需要注意的是，Lifecycle.Event.ON_START 和 Lifecycle.Event.ON_RESUME，这两个方法的调用会有一定的延后。
 * 这是因为系统需要为 “屏幕旋转，由于配置发生变化而导致 Activity 重新创建” 的情况预留一些时间。也就是说，系统需要保证当设备出现这种情况时，
 * 这两个事件不会被调用，因为当旋转屏幕时，你的应用程序并没有推到后台，它只是进入了横/竖屏模式而已
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

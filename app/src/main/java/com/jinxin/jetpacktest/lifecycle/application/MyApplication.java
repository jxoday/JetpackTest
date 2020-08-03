package com.jinxin.jetpacktest.lifecycle.application;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

/**
 * @author JinXin 2020/7/27
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // ProcessLifecycleOwner是针对整个应用程序的监听，与Activity数量无关
        // Lifecycle.Event.ON_CREATE 只会被调用一次，而Lifecycle.Event.ON_DESTROY永远不会被调用
        // 当应用程序从后台回到前台，或者应用程序被首次打开时，会依次调用Lifecycle.Event.ON_START和Lifecycle.Event.ON_RESUME
        // 当应用程序从前台回到后台（用户按下Home键或任务菜单键），会依次调用Lifecycle.Event.ON_PAUSE和Lifecycle.Event.ON_STOP
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationObserver());
    }
}

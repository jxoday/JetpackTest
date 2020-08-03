package com.jinxin.jetpacktest.lifecycle.service;

import androidx.lifecycle.LifecycleService;

/**
 * LifecycleService是Service的直接子类和普通Service没有差别
 *
 * @author JinXin 2020/7/27
 */
public class MyService extends LifecycleService {

    private MyServiceObserver myServiceObserver;

    public MyService() {

        myServiceObserver = new MyServiceObserver();
        // 将观察者和被观察者绑定
        getLifecycle().addObserver(myServiceObserver);
    }
}

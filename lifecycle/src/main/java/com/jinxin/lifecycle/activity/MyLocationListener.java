package com.jinxin.lifecycle.activity;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 自定义组件，让组件实现LifecycleObserver接口。
 * 与获取地理位置相关的代码在该类中完成
 * 对于组件中那些需要在页面周期发生变化时得到通知的方法，我们需要在这些方法上使用@OnLifecycleEvent(Lifecycle.Event.On_XXX)标签进行标识。
 * 这样，当页面生命周期发生变化时，这些被标识过的方法便会被自动调用
 *
 * @author JinXin 2020/7/26
 */
public class MyLocationListener implements LifecycleObserver {

    private static final String TAG = "MyLocationListener";

    private OnLocationChangedListener onLocationChangedListener;

    public MyLocationListener(Context context, OnLocationChangedListener onLocationChangedListener) {
        this.onLocationChangedListener = onLocationChangedListener;

        // 初始化操作
        initLocationManager();
    }

    private void initLocationManager() {

    }

    /**
     * 当Activity执行onResume()方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void startGetLocation() {
        Log.d(TAG, "startGetLocation: ");
        if (onLocationChangedListener != null) {
            onLocationChangedListener.onChanged(0,0);
        }
    }

    /**
     * 当Activity执行onPause()方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void stopGetLocation() {
        Log.d(TAG, "stopGetLocation: ");
        if (onLocationChangedListener != null) {
            onLocationChangedListener.onChanged(0,0);
        }
    }

    /**
     * 当地理位置发生变化时，通过该接口通知调用者
     */
    public interface OnLocationChangedListener {

        /**
         * 地理位置发生变化
         * @param latitude 纬度
         * @param longitude 经度
         */
        void onChanged(double latitude, double longitude);

    }
}


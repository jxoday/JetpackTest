package com.jinxin.mvvm;

import android.app.Application;

import com.jinxin.mvvm.api.Api;
import com.jinxin.mvvm.api.RetrofitClient;
import com.jinxin.mvvm.db.UserDatabase;

/**
 * @author JinXin 2020/10/18
 */
public class MvvmApplication extends Application {

    private static final String TAG = "MyApplication";
    private static UserDatabase userDatabase;
    private static Api api;

    @Override
    public void onCreate() {
        super.onCreate();
        userDatabase = UserDatabase.getInstance(this);
        api = RetrofitClient.getInstance().getApi();
    }

    public static UserDatabase getUserDatabase() {
        return userDatabase;
    }

    public static Api getApi() {
        return api;
    }
}

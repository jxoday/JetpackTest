package com.jinxin.mvvm.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.jinxin.mvvm.api.Api;
import com.jinxin.mvvm.db.UserDao;
import com.jinxin.mvvm.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author JinXin 2020/10/18
 */
public class UserRepository {

    private static final String TAG = "UserRepository";

    private UserDao userDao;

    private Api api;

    public UserRepository(UserDao userDao, Api api) {
        this.userDao = userDao;
        this.api = api;
    }

    public LiveData<User> getUser(final String name) {
        refresh(name);
        return userDao.getUserByName(name);
    }

    public void refresh(String userName) {
        api.getUser(userName).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                User user = response.body();
                boolean successful = response.isSuccessful();
                if (!successful || user == null) {
                    Log.d(TAG, "onResponse: 请求失败");
                    return;
                }
                insertUser(user);
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

            }
        });
    }

    private void insertUser(final User user) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(user);
            }
        });
    }
}

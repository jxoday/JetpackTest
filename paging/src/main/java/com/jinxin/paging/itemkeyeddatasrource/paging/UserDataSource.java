package com.jinxin.paging.itemkeyeddatasrource.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.jinxin.paging.itemkeyeddatasrource.api.GithubApi;
import com.jinxin.paging.itemkeyeddatasrource.api.RetrofitClient;
import com.jinxin.paging.itemkeyeddatasrource.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author JinXin 2020/10/11
 */
public class UserDataSource extends ItemKeyedDataSource<Integer, User> {

    private static final String TAG = "UserDataSource";

    public static final int PER_PAGE = 12;

    /**
     * 首次加载数据
     * @param params
     * @param callback
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<User> callback) {

        int since = 0;

        GithubApi githubApi = RetrofitClient.getInstance().getGithubApi();
        githubApi.getUsers(since, PER_PAGE)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        List<User> userList = response.body();
                        boolean successful = response.isSuccessful();
                        if (!successful || userList == null) {
                            Log.d(TAG, "loadInitial onResponse: 请求失败 " + response.message());
                            return;
                        }
                        Log.d(TAG, "loadInitial onResponse: 请求成功 " + userList);

                        callback.onResult(userList);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        Log.d(TAG, "loadInitial onFailure: 请求失败");
                    }
                });
    }

    /**
     * 加载下一页数据
     * @param params
     * @param callback
     */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<User> callback) {

        GithubApi githubApi = RetrofitClient.getInstance().getGithubApi();
        githubApi.getUsers(params.key, PER_PAGE)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        List<User> userList = response.body();
                        boolean successful = response.isSuccessful();
                        if (!successful || userList == null) {
                            Log.d(TAG, "loadAfter onResponse: 请求失败 " + response.message());
                            return;
                        }
                        Log.d(TAG, "loadAfter onResponse: 请求成功 " + userList);

                        callback.onResult(userList);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        Log.d(TAG, "loadAfter onFailure: 请求失败");
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<User> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull User item) {
        return item.id;
    }
}

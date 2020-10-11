package com.jinxin.paging.pagekeyeddatasource.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.jinxin.paging.pagekeyeddatasource.api.RetrofitClient;
import com.jinxin.paging.pagekeyeddatasource.api.StackOverflowApi;
import com.jinxin.paging.pagekeyeddatasource.model.User;
import com.jinxin.paging.pagekeyeddatasource.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author JinXin 2020/10/11
 */
public class UserDataSource extends PageKeyedDataSource<Integer, User> {

    private static final String TAG = "UserDataSource";

    public static final int FIRST_PAGE = 1;
    public static final int PER_PAGE = 8;
    public static final String SITE = "stackoverflow";

    /**
     * 首次加载调用
     * @param params params
     * @param callback 回调
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, User> callback) {

        StackOverflowApi stackOverflowApi = RetrofitClient.getInstance().getStackOverflowApi();
        stackOverflowApi.getUsers(FIRST_PAGE, PER_PAGE, SITE)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                        UserResponse body = response.body();
                        boolean successful = response.isSuccessful();
                        if (!successful || body == null) {
                            Log.d(TAG, "loadInitial onResponse: 请求失败 " + response.message());
                            return;
                        }
                        Log.d(TAG, "loadInitial onResponse: 请求成功 " + body);

                        // 第一个参数是加载得到的数据，将其交给 PagedList
                        // 第二个参数是上一页的key。在此，由于是加载的是第一页，不存在上一页，所以设置为 null
                        // 第三个参数为下一页的key，即当前页 key 的值 +1，若不存在下一页，则设置为null
                        callback.onResult(body.users, null, FIRST_PAGE + 1);
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                        Log.d(TAG, "loadInitial onFailure: 请求失败");
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {
        Log.d(TAG, "loadBefore: todo");
    }

    /**
     * 加载下一页
     * @param params 在 loadInitial()方法中设置的 nextPageKey,通过 LoadParams传递。LoadParams.key得到下一页的key
     * @param callback 请求下一页成功后，通过 callback.onResult()方法将数据返回给PagedList
     */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {

        StackOverflowApi stackOverflowApi = RetrofitClient.getInstance().getStackOverflowApi();
        stackOverflowApi.getUsers(params.key, PER_PAGE, SITE)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                        UserResponse body = response.body();
                        boolean successful = response.isSuccessful();
                        if (!successful || body == null) {
                            Log.d(TAG, "loadAfter onResponse: 请求失败 " + response.message());
                            return;
                        }
                        Log.d(TAG, "loadAfter onResponse: 请求成功 " + body);

                        // 判断是否还有更多数据，若没有数据，则将下一页的key设置为null，表示所有数据请求完毕
                        int nextKey = body.hasMore ? params.key + 1 : null;

                        callback.onResult(body.users, nextKey);
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                        Log.d(TAG, "loadAfter onFailure: 请求失败");
                    }
                });
    }
}

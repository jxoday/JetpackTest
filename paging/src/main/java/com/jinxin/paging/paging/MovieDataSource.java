package com.jinxin.paging.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.jinxin.paging.api.DouBanApi;
import com.jinxin.paging.api.RetrofitClient;
import com.jinxin.paging.model.Movie;
import com.jinxin.paging.model.Movies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * PositionalDataSource: 通过任意位置加载数据，且目标数据源数量固定
 * @author JinXin 2020/10/9
 */
public class MovieDataSource extends PositionalDataSource<Movie> {

    private static final String TAG = "MovieDataSource";

    public static final int PER_PAGE = 8;

    /**
     * 当页面首次加载数据时会调用 loadInitial()方法。
     * 在该方法内，调用api接口，并设置从第一条数据开始加载。
     * 加载成功后，需要通过 callback.onResult()方法将数据返回给PagedList。否则数据不会展示
     *
     * @param params
     * @param callback 回调
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<Movie> callback) {

        int startPosition = 0;

        DouBanApi douBanApi = RetrofitClient.getInstance().getDouBanApi();
        douBanApi.getMovies(startPosition, PER_PAGE)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(@NonNull Call<Movies> call, @NonNull Response<Movies> response) {

                        Movies body = response.body();
                        boolean successful = response.isSuccessful();
                        if (!successful || body == null) {
                            Log.d(TAG, "onResponse: 请求失败 " + response.message());
                            return;
                        }
                        Log.d(TAG, "onResponse: 请求成功 " + body);

                        // 在callback.onResult()方法中，需要注意的是第3个参数int totalCount。
                        // 如果在PagedList.Config中设置了 setEnablePlaceholders()方法的值为true，那么
                        // 需要通过 totalCount 参数告知 PagedList 当前服务端数据的总数，否则程序会报错。
                        // setEnablePlaceholders()方法的作用是，是否需要为那些‘数量已知，但尚未加载出来的数据’预留位置

                        callback.onResult(body.movieList, body.start, body.total);

                    }

                    @Override
                    public void onFailure(@NonNull Call<Movies> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: 请求失败");
                    }
                });
    }

    /**
     * loadInitial()方法的作用是负责第一页数据的加载，当第一页数据顺利加载后，
     * 加载下一页的工作会在 loadRange()方法内进行。加载成功后，仍然是通过callback.onResult()方法将数据返回给PagedList
     * @param params
     * @param callback 回调
     */
    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull final LoadRangeCallback<Movie> callback) {
        Log.d(TAG, "loadRange: 加载下一页 " + params.startPosition);
        DouBanApi douBanApi = RetrofitClient.getInstance().getDouBanApi();
        douBanApi.getMovies(params.startPosition, PER_PAGE)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(@NonNull Call<Movies> call, @NonNull Response<Movies> response) {
                        Movies body = response.body();
                        boolean successful = response.isSuccessful();
                        if (!successful || body == null) {
                            Log.d(TAG, "onResponse: 请求失败 " + response.message());
                            return;
                        }
                        Log.d(TAG, "onResponse: 请求成功 " + body);
                        callback.onResult(body.movieList);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Movies> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: 请求失败");
                    }
                });
    }
}

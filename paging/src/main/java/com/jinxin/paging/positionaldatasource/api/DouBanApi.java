package com.jinxin.paging.positionaldatasource.api;

import com.jinxin.paging.positionaldatasource.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 豆瓣api
 * @author JinXin 2020/10/9
 */
public interface DouBanApi {

    /**
     * 获取影院当前上映的电影
     * @param start 开始
     * @param count 单页条数
     * @return
     */
    @GET("movie/in_theaters")
    Call<Movies> getMovies(@Query("start") int start, @Query("count") int count);
}

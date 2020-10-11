package com.jinxin.paging.pagekeyeddatasource.api;

import com.jinxin.paging.pagekeyeddatasource.model.UserResponse;
import com.jinxin.paging.positionaldatasource.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * StackOverflowApi
 * @author JinXin 2020/10/10
 */
public interface StackOverflowApi {


    /**
     * 用户列表
     * @param page 页数
     * @param pageSize 每页数据
     * @param site 数据来源
     * @return
     */
    @GET("users")
    Call<UserResponse> getUsers(@Query("page") int page,
                                @Query("pagesize") int pageSize,
                                @Query("site") String site);
}

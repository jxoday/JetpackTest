package com.jinxin.mvvm.api;

import com.jinxin.mvvm.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author JinXin 2020/10/18
 */
public interface Api {

    /**
     * 获取用户
     * @param userId 用户id
     * @return 用户信息
     */
    @GET("users/{userId}")
    Call<User> getUser(@Path("userId")String userId);

}

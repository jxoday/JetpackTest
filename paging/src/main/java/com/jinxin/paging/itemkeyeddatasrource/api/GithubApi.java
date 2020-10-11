package com.jinxin.paging.itemkeyeddatasrource.api;


import com.jinxin.paging.itemkeyeddatasrource.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * GithubApi
 * @author JinXin 2020/10/10
 */
public interface GithubApi {


    /**
     * 用户列表
     * @param since 表示数据对象 item中的某个字段，以字段作为请求下一页的 key
     * @param perPage 表示 since所指代的对象之后的 6条数据
     * @return 用户列表
     */
    @GET("users")
    Call<List<User>> getUsers(@Query("since") int since,
                              @Query("per_page") int perPage);
}

package com.jinxin.paging.positionaldatasource.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * call api 请求公共参数
 * @author JinXin 2020/10/9
 */
public class ApiInterceptor implements Interceptor {

    private static final String TAG = "ApiInterceptor";
    public static final String API_KEY = "0df993c66c0c636e29ecbb5344252a4a";


    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request oldRequest = chain.request();
        // 为每一个请求添加公共参数
        Request request = processRequest(oldRequest);

        // 请求
        return chain.proceed(request);
    }


    private Request processRequest(Request oldRequest) {

        HttpUrl httpUrl = oldRequest.url().newBuilder()
                .addQueryParameter("apikey", API_KEY)
                .build();

        return oldRequest.newBuilder()
                .url(httpUrl)
                .build();

    }
}

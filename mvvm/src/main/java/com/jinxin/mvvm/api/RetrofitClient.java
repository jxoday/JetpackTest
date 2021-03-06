package com.jinxin.mvvm.api;

import com.jinxin.mvvm.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author JinXin 2020/10/18
 */
public class RetrofitClient {
    public static final String BASE_URL = "http://api.github.com/";

    private static RetrofitClient retrofitClient;

    private Retrofit retrofit;

    public RetrofitClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();

    }

    public static synchronized RetrofitClient getInstance() {

        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    /**
     * 获取httpclient实例, 双检查机制, 线程安全
     * 增加服务地址拦截器和接口公共参数拦截器
     * @return httpClient
     */
    private OkHttpClient getClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            //调试模式, 打开网络请求日志
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            httpClient.addInterceptor(loggingInterceptor);
        }
        return httpClient.build();
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}

package com.ranlychan.ktv.service;

import com.ranlychan.ktv.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder okHttpclientBuilder;
    private static Retrofit.Builder retrofitBuilder;

    public static <S> S createService(Class<S> serviceClass) {
        okHttpclientBuilder = new OkHttpClient.Builder();
        //目前封装的几个接口都是没有用到GsonConverter，是传入ResponseBody，拿到响应结果后自己解析
        retrofitBuilder = new Retrofit.Builder() .baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.client(okHttpclientBuilder.build()).build();
        return retrofit.create(serviceClass);
    }
}
package com.semonemo.latte.toyouproject.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by latte on 2018. 1. 17..
 */

public class Retrofit2ServiceFactory<T> {
    private String baseUrl;
    private Class<T> clazz;
    private int timeoutInSecond = 10;

    public Retrofit2ServiceFactory(String baseUrl, Class<T> clazz){
        this.baseUrl = baseUrl;
        this.clazz = clazz;
    }

    public Retrofit2ServiceFactory<T> setTimeoutInSecond(int timeout) {
        this.timeoutInSecond = timeout;
        return this;
    }

    public T build() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(timeoutInSecond, TimeUnit.SECONDS)
                .connectTimeout(timeoutInSecond, TimeUnit.SECONDS);

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit2 =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        return retrofit2.create(clazz);
    }
}

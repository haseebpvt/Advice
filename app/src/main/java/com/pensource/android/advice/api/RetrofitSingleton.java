package com.pensource.android.advice.api;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static volatile Retrofit INSTANCE = null;

    private RetrofitSingleton() {}

    public static Retrofit getInstance(String BASE_URL) {
        if (INSTANCE == null) {
            synchronized (RetrofitSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

package com.pensource.android.advice.api;

import com.pensource.android.advice.api.model.AdviceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://api.adviceslip.com/";

    @GET("advice")
    Call<AdviceResponse> getAdvice();

}

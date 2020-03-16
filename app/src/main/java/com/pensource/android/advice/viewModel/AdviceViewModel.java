package com.pensource.android.advice.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pensource.android.advice.api.Api;
import com.pensource.android.advice.api.RetrofitSingleton;
import com.pensource.android.advice.api.model.AdviceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdviceViewModel extends ViewModel {

    private MutableLiveData<AdviceResponse> advice;

    public MutableLiveData<AdviceResponse> getAdvice() {
        if (advice == null) {
            advice = new MutableLiveData<>();

            loadAdvice();
        }
        return advice;
    }

    public void loadAdvice() {
        Retrofit retrofit = RetrofitSingleton.getInstance(Api.BASE_URL);
        Api api = retrofit.create(Api.class);
        Call<AdviceResponse> call = api.getAdvice();
        call.enqueue(new Callback<AdviceResponse>() {
            @Override
            public void onResponse(Call<AdviceResponse> call, Response<AdviceResponse> response) {

                if (response.body() != null) {
                    // Response not null
                    advice.setValue(response.body());
                } else {
                    // Response null
                }

            }

            @Override
            public void onFailure(Call<AdviceResponse> call, Throwable t) {
                // Response failed

            }
        });
    }

}

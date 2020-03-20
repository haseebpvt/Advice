package com.pensource.android.advice.worker;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.pensource.android.advice.api.Api;
import com.pensource.android.advice.api.RetrofitSingleton;
import com.pensource.android.advice.api.model.AdviceResponse;
import com.pensource.android.advice.database.model.Advice;
import com.pensource.android.advice.database.repository.AdviceRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdviceWorker extends Worker {

    private int NUMBER_OF_ADVICE = 10;

    private static final String TAG = "worker_log";
    private AdviceRepository adviceRepository;

    public AdviceWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        adviceRepository = new AdviceRepository((Application) getApplicationContext());
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Work started");

        List<Advice> adviceList = getListOfAdvice(NUMBER_OF_ADVICE);

        Log.d(TAG, "Adding to database");
        adviceRepository.insertAdviceList(adviceList);

        if (adviceList.size() < NUMBER_OF_ADVICE) return Result.retry();
        else if (adviceList.size() == NUMBER_OF_ADVICE)return Result.success();
        else return Result.failure();
    }

    private synchronized List<Advice> getListOfAdvice(int numberOfAdvice) {
        List<Advice> adviceList = new ArrayList<>();

        Retrofit retrofit = RetrofitSingleton.getInstance(Api.BASE_URL);
        Api api = retrofit.create(Api.class);

        for (int i = 0; i < numberOfAdvice; i++) {
            Log.d(TAG, "Position: " + i);

            try {
                Call<AdviceResponse> call = api.getAdvice();
                Response<AdviceResponse> adviceResponseResponse = call.execute();

                Log.d(TAG, "Response: " + adviceResponseResponse.toString());

                Advice advice = new Advice();
                advice.advice_id = adviceResponseResponse.body().getSlip().getSlip_id();
                advice.advice = adviceResponseResponse.body().getSlip().getAdvice();
                adviceList.add(advice);

            } catch (IOException e) {
                e.printStackTrace();

                break;
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return adviceList;
    }

}

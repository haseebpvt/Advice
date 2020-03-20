package com.pensource.android.advice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.TimeUtils;
import android.widget.TextView;

import com.pensource.android.advice.api.model.AdviceResponse;
import com.pensource.android.advice.database.repository.AdviceRepository;
import com.pensource.android.advice.viewModel.AdviceViewModel;
import com.pensource.android.advice.worker.AdviceWorker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView adviceTextView;

    private AdviceViewModel adviceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adviceTextView = findViewById(R.id.adviceTextView);

        adviceViewModel = ViewModelProviders.of(this).get(AdviceViewModel.class);
        adviceViewModel.getAdvice().observe(this, adviceResponse -> adviceTextView.setText(adviceResponse.getSlip().getAdvice()));

        adviceTextView.setOnClickListener(v -> adviceViewModel.loadAdvice());

//        Constraints constraints = new Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build();
//
//        OneTimeWorkRequest adviceWork = new OneTimeWorkRequest.Builder(AdviceWorker.class)
//                .setConstraints(constraints)
//                .setInitialDelay(30, TimeUnit.SECONDS)
//                .build();
//
//        WorkManager.getInstance(this).enqueue(adviceWork);

    }
}

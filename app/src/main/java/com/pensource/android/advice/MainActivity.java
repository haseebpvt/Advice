package com.pensource.android.advice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.pensource.android.advice.api.model.AdviceResponse;
import com.pensource.android.advice.viewModel.AdviceViewModel;

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

    }
}

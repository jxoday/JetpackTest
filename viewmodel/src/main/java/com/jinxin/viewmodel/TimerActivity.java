package com.jinxin.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        initComponent();
    }

    private void initComponent() {
        final TextView tvTimer = findViewById(R.id.tv_timer);
        // 实例化ViewModel
        TimerViewModel testViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        testViewModel.setOnTimerChangeListener(currentSecond -> {
            // 更新UI界面
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvTimer.setText("Timer: " + currentSecond);
                }
            });
        });
        testViewModel.startTiming();
    }
}
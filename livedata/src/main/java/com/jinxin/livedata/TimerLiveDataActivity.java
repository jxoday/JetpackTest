package com.jinxin.livedata;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

public class TimerLiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_live_data);

        final TextView tvTimer = findViewById(R.id.tv_timer);
        final Button btnReset = findViewById(R.id.btn_reset_time);

        // 通过 ViewModelProvider 得到 ViewModel
        TimerLiveDataViewModel timerLiveDataViewModel = new ViewModelProvider(this).get(TimerLiveDataViewModel.class);

        // 得到 ViewModel 中的 LiveData
        MutableLiveData<Integer> liveCurrentSecond = timerLiveDataViewModel.getLiveCurrentSecond();

        // 通过 LiveData.Observer() 观察 ViewModel 中数据的变化
        liveCurrentSecond.observe(this, second -> {
            tvTimer.setText(String.valueOf(second));
        });

        // 重置计时器
        btnReset.setOnClickListener(v -> {
            // 通过 LiveData.setValue()/LiveData.postValue()
            // 完成对 viewModel 中数据的更新
            liveCurrentSecond.setValue(0);
        });

        //  计时开始
        timerLiveDataViewModel.startTiming();
    }
}
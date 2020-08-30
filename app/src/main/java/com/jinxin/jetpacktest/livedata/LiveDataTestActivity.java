package com.jinxin.jetpacktest.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jinxin.jetpacktest.R;

/**
 * LiveData学习
 *
 * LiveData介绍：LiveData是一个可被观察的数据容器类。具体来说，可以将LiveData理解为一个数据的容器，
 * 它将数据包装起来，使数据成为被观察者，当该数据发生变化时，观察者能够获得通知。我们不需要自己去实现观察者模式，
 * LiveData内部已经默认实现好了，我们只要使用就好了
 * @author JinXin
 */
public class LiveDataTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_test);

        initComponent();
    }

    private void initComponent() {

        // 通过ViewModelProvider得到LiveDataViewModel
        LiveDataViewModel liveDataViewModel = new ViewModelProvider(this).get(LiveDataViewModel.class);
        // 得到ViewModel中的LiveData
        final MutableLiveData<Integer> liveCurrentSecond = liveDataViewModel.getLiveCurrentSecond();
        // 通过LiveData.observe()观察ViewModel中数据的变化
        liveCurrentSecond.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer second) {
                ((TextView)findViewById(R.id.tv_second)).setText("Timer: " + second);
            }
        });

        findViewById(R.id.btn_reset_timer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过LiveData.setValue()/LiveData.postValue()
                // 完成对viewModel中数据的更新
                // 注：setValue使用在UI线程；postValue使用在非UI线程
                liveCurrentSecond.setValue(0);
            }
        });

        // 计时开始
        liveDataViewModel.startTiming();
    }
}
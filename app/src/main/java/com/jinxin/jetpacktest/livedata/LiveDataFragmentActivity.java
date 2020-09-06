package com.jinxin.jetpacktest.livedata;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.jinxin.jetpacktest.R;

/**
 * @author JinXin
 */
public class LiveDataFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_fragment);

        // 利用LiveData实现同一个Activity中的不同Fragment间的通信

        // 当前Activity中有两个Fragment：OneFragment、TwoFragment
        // 两个Fragment中都有一个SeekBar控件
        // 当用户操作其中一个Fragment中的SeekBar时，更新ViewModel中的数据
        // 无论是滑动OneFragment还是TwoFragment中的SeekBar,另一个Fragment中的SeekBar也会跟着滑动。
        // 在滑动SeekBar时，通过LiveData.setValue()方法，修改了ViewModel中LiveData包装的数据(progress字段)。
        // 由于Fragment通过LiveData.observe()方法监听了数据的变化，因此Progress字段被修改后，Fragment能够第一时间
        // 收到通知并更新UI。这就是利用ViewModel和LiveData实现Fragment间通信的原理
    }
}
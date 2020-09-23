package com.jinxin.jetpacktest.databinding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jinxin.jetpacktest.R;

/**
 * DataBinding 双向绑定
 *
 *
 * @author JinXin
 */
public class TowWayBindingActivity extends AppCompatActivity {

    private static final String TAG = "DataBindingTestActivity";
    private ActivityTowWayBindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tow_way_binding);
        binding.setViewModel(new TowWayBindingViewModel());
        binding.setSimpleViewModel(new TowWaySimpleBindingViewModel());
    }
}
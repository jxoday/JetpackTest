package com.jinxin.livedata.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.jinxin.livedata.R;

/**
 * @author JinXin
 */
public class TwoFragment extends Fragment {

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        initView(view);
        return view.getRootView();
    }

    private void initView(View root) {
        final SeekBar seekBar = root.findViewById(R.id.seek_bar);

        // 注意：这里 ViewModelProvider(getActivity())中的参数
        // 需要的是 Activity，而不是 Fragment， 否则将收不到监听
        ShareDataViewModel shareDataViewModel = new ViewModelProvider(getActivity()).get(ShareDataViewModel.class);

        MutableLiveData<Integer> liveDataProgress = shareDataViewModel.getProgress();

        // 通过 observer 方法观察 ViewModel 中字段数据的变化，并在变化时得到通知
        liveDataProgress.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer progress) {
                seekBar.setProgress(progress);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 当用户操作　SeekBar 时，更新 ViewModel 中的数据
                liveDataProgress.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
package com.jinxin.jetpacktest.livedata;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.jinxin.jetpacktest.R;

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
        View root = inflater.inflate(R.layout.fragment_two, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        final SeekBar seekBar = root.findViewById(R.id.seek_bar);

        // 注意：这里ViewModelProvider(getActivity())中的参数
        // 需要的是Activity，而不是Fragment，否则将收不到监听
        LiveDataFragmentViewModel viewModel = new ViewModelProvider(this.getActivity()).get(LiveDataFragmentViewModel.class);

        final MutableLiveData<Integer> liveProgress = viewModel.getProgress();

        // 通过observe方法观察ViewModel中字段数据变化，并在变化时得到通知
        liveProgress.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                seekBar.setProgress(integer);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 将用户操作SeekBar时，更新ViewModel中的数据
                liveProgress.setValue(progress);
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
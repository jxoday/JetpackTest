package com.jinxin.jetpacktest.databinding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jinxin.jetpacktest.R;

/**
 * RecyclerView的绑定机制
 * RecycleView有自己的一套绑定机制。它通过RecycleView.Adapter实现RecycleView与数据源List<T>之间的绑定。
 * 在RecycleView.Adapter的回调方法中，可以利用DataBinding库帮助实例化RecycleView中每个item的布局文件，
 * 进而将布局文件中的控件与List<T>中的类型对象 T 进行绑定
 *
 * @author JinXin
 */
public class RecyclerViewBindingActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewBindingActivity";
    private ActivityRecyclerViewBindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view_binding);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(new RecyclerViewViewModel().getBooks());
        binding.recyclerView.setAdapter(recyclerViewAdapter);
    }
}
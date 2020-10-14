package com.jinxin.paging.boundarycallback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.jinxin.paging.R;
import com.jinxin.paging.boundarycallback.paging.UserViewModel;
import com.jinxin.paging.boundarycallback.paging.UserAdapter;

/**
 * @author JinXin
 */
public class BoundaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boundary);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        RecyclerView recyclerUser = findViewById(R.id.recycle_user);
        recyclerUser.setLayoutManager(new LinearLayoutManager(this));
        // 当知道Adapter内Item的改变不会影响RecyclerView宽高的时候，可以设置为true让RecyclerView避免重新计算大小。
        recyclerUser.setHasFixedSize(true);
        final UserAdapter userAdapter = new UserAdapter();
        recyclerUser.setAdapter(userAdapter);

        // 展示数据
        userViewModel.userPagedList.observe(this, userAdapter::submitList);

        // 下拉刷新
        swipeRefreshLayout.setOnRefreshListener(() -> {
            userViewModel.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });
    }
}
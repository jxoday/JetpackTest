package com.jinxin.paging.pagekeyeddatasource;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jinxin.paging.R;
import com.jinxin.paging.pagekeyeddatasource.model.User;
import com.jinxin.paging.pagekeyeddatasource.paging.UserAdapter;
import com.jinxin.paging.pagekeyeddatasource.paging.UserViewModel;
import com.jinxin.paging.positionaldatasource.model.Movie;
import com.jinxin.paging.positionaldatasource.paging.MovieAdapter;
import com.jinxin.paging.positionaldatasource.paging.MovieViewModel;

/**
 * @author JinXin
 */
public class PageKeyedActivity extends AppCompatActivity {

    private static final String TAG = "PageKeyedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_keyed);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        RecyclerView recyclerUser = findViewById(R.id.recycle_user);
        recyclerUser.setLayoutManager(new LinearLayoutManager(this));
        // 当知道Adapter内Item的改变不会影响RecyclerView宽高的时候，可以设置为true让RecyclerView避免重新计算大小。
        recyclerUser.setHasFixedSize(true);
        final UserAdapter userAdapter = new UserAdapter();
        recyclerUser.setAdapter(userAdapter);

        userViewModel.userPagedList.observe(this, userAdapter::submitList);
    }
}
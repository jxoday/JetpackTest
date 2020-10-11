package com.jinxin.paging.itemkeyeddatasrource;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jinxin.paging.R;
import com.jinxin.paging.itemkeyeddatasrource.paging.UserAdapter;
import com.jinxin.paging.itemkeyeddatasrource.paging.UserViewModel;

/**
 * @author JinXin
 */
public class ItemKeyedMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_keyed_main);

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
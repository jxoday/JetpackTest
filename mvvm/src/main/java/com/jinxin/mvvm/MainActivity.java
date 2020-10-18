package com.jinxin.mvvm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jinxin.mvvm.databinding.ActivityMvvmMainBinding;
import com.jinxin.mvvm.model.User;
import com.jinxin.mvvm.viewmodel.UserViewModel;

/**
 * @author JinXin
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMvvmMainBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_main);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user == null) {
                    return;
                }
                binding.setUser(user);
            }
        });

        binding.refresh.setOnRefreshListener(() -> {
            userViewModel.refresh();
            binding.refresh.setRefreshing(false);
        });

    }
}
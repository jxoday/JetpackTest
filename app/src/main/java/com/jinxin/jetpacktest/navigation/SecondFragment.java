package com.jinxin.jetpacktest.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinxin.jetpacktest.R;

/**
 * @author JinXin
 */
public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        View root = inflater.inflate(R.layout.fragment_sceond, container, false);

        initView(root);
        return root.getRootView();
    }

    private void initView(View root) {

        // Fragment常见的接受参数方式
        Bundle bundle = getArguments();
        if (bundle != null) {

            String userName = bundle.getString("user_name");
            int age = bundle.getInt("age");
            TextView tvUserName = root.findViewById(R.id.tv_user_name);
            TextView tvAge = root.findViewById(R.id.tv_age);

            tvUserName.setText(userName);
            tvAge.setText(String.valueOf(age));


        }
    }
}
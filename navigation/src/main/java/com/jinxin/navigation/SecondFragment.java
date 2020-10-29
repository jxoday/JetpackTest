package com.jinxin.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author JinXin
 */
public class SecondFragment extends Fragment {

    public SecondFragment() {
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
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        TextView tvUserName = view.findViewById(R.id.tv_user_name);
        TextView tvAge = view.findViewById(R.id.tv_age);
        Bundle arguments = getArguments();
        if (arguments != null) {
            // 常见的接收参数方式
//            String userName = arguments.getString("user_name");
//            int age = arguments.getInt("age");

            // 使用 safe args 接收参数方式
            SecondFragmentArgs secondFragmentArgs = SecondFragmentArgs.fromBundle(arguments);
            String userName = secondFragmentArgs.getUserName();
            int age = secondFragmentArgs.getAge();
            tvUserName.setText(userName);
            tvAge.setText(String.valueOf(age));
        }
        return view;
    }
}
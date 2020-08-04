package com.jinxin.jetpacktest.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinxin.jetpacktest.R;

/**
 * @author JinXin
 */
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        initView(root);
        return root;
    }

    private void initView(View root) {

        // 跳转界面的两种方式
        // 方法一
        root.findViewById(R.id.btn_to_second_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fragment常见的传递参数方式
//                Bundle bundle = new Bundle();
//                bundle.putString("user_name", "JinXin");
//                bundle.putInt("age", 24);

                // 使用safe args传递参数
                Bundle bundle = new MainFragmentArgs.Builder()
                        .setAge(10)
                        .setUserName("jinxin")
                        .build().toBundle();

                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_sceondFragment, bundle);
            }
        });

        // 方法二
//        root.findViewById(R.id.btn_to_second_fragment)
//                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_sceondFragment));
    }
}
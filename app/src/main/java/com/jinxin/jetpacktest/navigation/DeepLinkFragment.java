package com.jinxin.jetpacktest.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jinxin.jetpacktest.R;

/**
 *
 * @author JinXin
 */
public class DeepLinkFragment extends Fragment {

    public DeepLinkFragment() {
        // Required empty public constructor
    }

    public static DeepLinkFragment newInstance(String param1, String param2) {
        DeepLinkFragment fragment = new DeepLinkFragment();
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
        View root = inflater.inflate(R.layout.fragment_deep_link, container, false);

        initView(root);
        return root;
    }

    private void initView(View root) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            String params = bundle.getString("params");

            TextView tvTitle = root.findViewById(R.id.tv_title);
            tvTitle.setText(params);
        }
    }
}
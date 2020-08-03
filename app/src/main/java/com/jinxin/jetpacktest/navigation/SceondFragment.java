package com.jinxin.jetpacktest.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinxin.jetpacktest.R;

/**
 * @author JinXin
 */
public class SceondFragment extends Fragment {

    public SceondFragment() {
        // Required empty public constructor
    }

    public static SceondFragment newInstance(String param1, String param2) {
        SceondFragment fragment = new SceondFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sceond, container, false);
    }
}
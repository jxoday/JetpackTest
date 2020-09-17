package com.jinxin.jetpacktest.databinding;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * @author JinXin 2020/9/17
 */
public class EventHandleListener {

    private Context context;

    public EventHandleListener(Context context) {
        this.context = context;
    }

    public void onButtonClicked(View view) {
        Toast.makeText(context, "I am clicked", Toast.LENGTH_SHORT).show();
    }
}

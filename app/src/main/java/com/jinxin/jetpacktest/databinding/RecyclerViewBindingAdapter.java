package com.jinxin.jetpacktest.databinding;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.jinxin.jetpacktest.R;
import com.squareup.picasso.Picasso;

/**
 * @author JinXin 2020/9/23
 */
public class RecyclerViewBindingAdapter {

    @BindingAdapter("itemImage")
    public static void setImage(ImageView imageView, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageView);
        } else {
            imageView.setBackgroundResource(R.drawable.guidao);
        }
    }
}

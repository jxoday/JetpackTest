package com.jinxin.mvvm.bindingadapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.jinxin.mvvm.R;
import com.squareup.picasso.Picasso;

/**
 * @author JinXin 2020/10/18
 */
public class ImageViewBindingAdapter {

    @BindingAdapter(value = {"image", "defaultImageResource"}, requireAll = false)
    public static void setImage(ImageView imageView, String imageUrl, int imageResource) {
        if (TextUtils.isEmpty(imageUrl)) {
            imageView.setImageResource(imageResource);
        } else {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.invitation_8k)
                    .placeholder(R.drawable.treasure_8k)
                    .into(imageView);
        }
    }
}

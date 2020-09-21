package com.jinxin.jetpacktest.databinding;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.jinxin.jetpacktest.R;
import com.squareup.picasso.Picasso;

/**
 * 自定义BindingAdapter的基本用法
 * @author JinXin 2020/9/21
 */
public class ImageViewBindingAdapter {

    private static final String TAG = "ImageViewBindingAdapter";

    /**
     * 自定义方法实现显示网络图片
     * @param imageView
     * @param imageUrl
     */
    @BindingAdapter("image")
    public static void setImage(ImageView imageView, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageView);
        } else {
            imageView.setBackgroundColor(Color.DKGRAY);
        }
    }

    /**
     * 方法重载
     *
     * 显示项目资源文件中的图片。通过对这个方法进行重载实现
     *
     * @param imageView
     * @param imageResource
     */
    @BindingAdapter("image")
    public static void setImage(ImageView imageView, int imageResource) {
        imageView.setImageResource(imageResource);
    }

    /**
     * 方法多参数重载
     *
     * 上面BindingAdapter示例中的两个方法，分别接收网络图片地址和本地图片资源ID作为参数。
     * 如果要将这两个方法合并成一个方法，那么就需要用到多参数重载，将两个参数同时传入方法中。
     * 当网络图片地址为空时，则显示imageResource参数所指定的图片
     *
     * 在@BindingAdapter标签中，方法的参数以value={"", ""}的形式存在。变量requireAll用于
     * 告诉DataBinding库这些参数是否都要赋值，默认值为true，即全部需要赋值
     * 赋值方式如下方代码所示，当networkImage为空时，ImageView会显示localImage所指定的图片
     *
     * @param imageView
     * @param imageUrl
     * @param imageResource
     */
    @BindingAdapter(value = {"image", "defaultImageResource"}, requireAll = false)
    public static void setImage(ImageView imageView, String imageUrl, int imageResource) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageView);
        } else {
            imageView.setBackgroundColor(Color.DKGRAY);
        }
    }

    /**
     * 可选旧值
     *
     * 在某些情况下，你可能希望在方法中得到该属性的旧值。例如，在修改控件的padding时，
     * 希望得到修改前的padding，以防止方法重复调用。
     *
     * 需要注意的是，使用可选旧值时，方法中的参数顺序需要先写旧值，后写新值。即oldPadding在前，newPadding在后
     * @param view
     * @param oldPadding
     * @param newPadding
     */
    @BindingAdapter("padding")
    public static void setPadding(View view, int oldPadding, int newPadding) {
        Log.d(TAG, "setPadding: oldPadding：" + oldPadding + " newPadding:" + newPadding);
        if (oldPadding != newPadding) {
            view.setPadding(newPadding, newPadding, newPadding, newPadding);
        }
    }
}

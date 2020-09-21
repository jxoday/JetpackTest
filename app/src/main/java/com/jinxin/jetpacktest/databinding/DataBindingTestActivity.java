package com.jinxin.jetpacktest.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jinxin.jetpacktest.BR;
import com.jinxin.jetpacktest.R;

/**
 * DataBinding 优势
 * 1.项目更简洁，可读性更高。部分与UI控件相关的代码可以在布局文件中完成。
 * 2.不再需要findViewById()方法
 * 3.布局文件可以包含简单的业务逻辑。UI控件能够直接与数据模型中的字段绑定，甚至能够响应用户的交互
 *
 * @author JinXin
 */
public class DataBindingTestActivity extends AppCompatActivity {

    private static final String TAG = "DataBindingTestActivity";
    private ActivityDataBindingTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_test);

        // 简单的绑定
        Book book = new Book("DataBinding学习", "JinXin", 1);
        binding.setBook(book);

        // 响应事件
        binding.setEventHandler(new EventHandleListener(this));

        binding.setNetworkImage("https://tvax3.sinaimg.cn/large/9afb97dagy1ghovqlnjatj20u01fmhdt.jpg");

        binding.setLocalImage(R.drawable.guidao);

        // 旧值
        binding.setImagePadding(10);

        binding.setClickHandler(new ClickHandle());
    }

    public class ClickHandle {
        public void onClick(View view) {
            // 新值
            binding.setImagePadding(180);
        }
    }
}
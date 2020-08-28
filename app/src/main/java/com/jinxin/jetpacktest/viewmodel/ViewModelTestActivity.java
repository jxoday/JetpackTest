package com.jinxin.jetpacktest.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.jinxin.jetpacktest.R;

import org.w3c.dom.Text;

/**
 * ViewModel练习
 *
 * ViewModel类，专门用于存放应用程序页面所需的数据。
 * ViewModel将页面所需的数据从页面中剥离出来，页面只需要处理用户交互和展示数据
 *
 * ViewModel可以理解为：它是介于View(视图)和Model(数据模型)之间的一个东西。
 * 它起到了桥梁的作用，使视图和数据既能分离开，也能够保持通信
 *
 * 屏幕旋转导致的Activity重建，并不会影响ViewModel的生命周期,ViewModel是独立于页面存在的。
 * 正因为如此，在使用ViewModel过程中，需要特别注意，不要向ViewModel中传入任何类型的Context或带有Context引用的对象，
 * 这可能会导致页面无法被销毁，从而引发内存泄漏
 *
 * 如果希望在ViewModel中使用Context，可以使用AndroidViewModel类，它继承自ViewModel，并接受Application作为Context。
 * 它的生命周期和Application是一样的，那就不算是内存泄漏了
 * @author JinXin
 */
public class ViewModelTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model_test);

        initComponent();
    }

    private void initComponent() {

        final TextView tvSecond = findViewById(R.id.tv_second);
        // 实例化ViewModel
        TestViewModel testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        testViewModel.setOnTimerChangeListener(new TestViewModel.OnTimerChangeListener() {
            @Override
            public void onTimeChanged(final int currentSecond) {
                // 更新UI界面
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSecond.setText("Timer: " + currentSecond);
                    }
                });
            }
        });
        testViewModel.startTiming();
    }
}
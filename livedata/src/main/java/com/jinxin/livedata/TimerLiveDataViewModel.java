package com.jinxin.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author JinXin 2020/11/3
 */
public class TimerLiveDataViewModel extends ViewModel {

    private static final String TAG = "TimerViewModel";

    private Timer timer;
    private int currentSecond;

    /**
     * 将 currentSecond 这个字段用 MutableLiveData 包装起来
     */
    private MutableLiveData<Integer> liveCurrentSecond;

    public MutableLiveData<Integer> getLiveCurrentSecond() {
        if (liveCurrentSecond == null) {
            liveCurrentSecond = new MutableLiveData<>();
        }
        return liveCurrentSecond;
    }

    /**
     * ViewModel最重要的作用是将视图与数据分离，并独立于Activity的重建。
     * 为了验证这样一点，在ViewModel中创建一个计时器Timer，每隔1s通过 liveData 通知观察者更UI
     */
    public void startTiming() {

        if (timer == null) {

            currentSecond = 0;

            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    currentSecond ++;
                    getLiveCurrentSecond().postValue(currentSecond);
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        }
    }


    /**
     * ViewModel是一个抽象类，其中只有一个onCleared()方法。
     * 当ViewModel不再被需要，即与之相关的Activity都被销毁时，
     * 该方法会被系统调用。可以在该方法中执行一些资源释放相关操作
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        timer.cancel();
    }
}

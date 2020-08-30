package com.jinxin.jetpacktest.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * LiveData和ViewModel的关系
 *
 * ViewModel用于存放页面所需要的各种数据，不仅如此，我们还可以在其中放一些与数据相关的业务逻辑。
 * 对于页面来说，它并不关心ViewModel中的业务逻辑，它只关心需要展示的数据是什么，并且希望在数据发生变化时，能及时得到通知并作出更新。
 * LiveData的作用就是，在ViewModel中的数据发生变化时通知页面。因此，LiveData通常被放在ViewModel中使用，
 * 用于包装ViewModel中那些需要被外界观察的数据
 *
 * @author JinXin 2020/8/30
 */
public class LiveDataViewModel extends ViewModel {

    private static final String TAG = "LiveDataViewModel";
    private Timer timer;
    private int currentSecond;

    /**
     * 将currentSecond这个字段用MutableLiveData包装起来
     * LiveData是一个抽象类，不能直接使用。通常使用的是它的直接子类MutableLiveData
     **/
    private MutableLiveData<Integer> liveCurrentSecond;

    public MutableLiveData<Integer> getLiveCurrentSecond() {
        if (liveCurrentSecond == null) {
            liveCurrentSecond = new MutableLiveData<>();
        }
        return liveCurrentSecond;
    }

    /**
     * ViewModel最重要的作用是将视图与数据分离，并独立于Activity的重建。
     * 为了验证这样一点，在ViewModel中创建一个计时器Timer，每隔1s通过LiveData通知观察者更新UI
     */
    public void startTiming() {

        if (timer == null) {

            currentSecond = 0;

            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    currentSecond++;
                    getLiveCurrentSecond().postValue(currentSecond);
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        }
    }
}

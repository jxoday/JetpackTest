package com.jinxin.jetpacktest.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author JinXin 2020/9/6
 */
public class LiveDataFragmentViewModel extends ViewModel {

    private MutableLiveData<Integer> progress;
    public MutableLiveData<Integer> getProgress() {
        if (progress == null) {
            progress = new MutableLiveData<>();
        }
        return progress;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        progress = null;
    }
}

package com.jinxin.livedata.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author JinXin 2020/11/15
 */
public class ShareDataViewModel extends ViewModel {

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

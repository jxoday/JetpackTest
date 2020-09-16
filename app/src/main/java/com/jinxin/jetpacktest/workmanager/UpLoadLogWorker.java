package com.jinxin.jetpacktest.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * 使用Worker类定义任务
 * 假设要实现将日志上传到服务器的需求，新建一个名为UploadLogWorker的类，
 * 该类继承自Worker类，并且覆盖doWork()方法，所有需要在任务中执行的代码都在该方法中进行编写
 *
 * @author JinXin 2020/9/15
 */
public class UpLoadLogWorker extends Worker {

    private static final String TAG = "UpLoadLogWorker";

    public UpLoadLogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Log.d(TAG, "UpLoadLogWorker: ");
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: ");
        // 接受外面传递进来的数据
        String inputData = getInputData().getString("inputData");

        // 任务执行完成后返回数据
        Data outPutData = new Data.Builder()
                .putString("outputData", "任务执行成功")
                .build();

        // doWork()有三种类型的返回值
        // 执行成功 返回Result.success()
        // 执行失败 返回Result.failure()
        // 需要重新执行 返回Result.retry()
        return Result.success(outPutData);
    }
}

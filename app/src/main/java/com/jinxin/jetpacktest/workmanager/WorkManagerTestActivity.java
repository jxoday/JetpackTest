package com.jinxin.jetpacktest.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jinxin.jetpacktest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * WorkManager的作用
 * WorkManager为应用程序种那些不需要及时完成的任务提供了一个统一的解决方案，已便在设备电量和用户体验之间达到一个较好的平衡
 *
 * WorkManager的3个重要特点
 * 1.针对的是不需要及时完成的任务
 * 例如，发送应用程序日志、同步应用程序数据、备份用户数据等，站在业务需求的角度，这些任务都不需要立即完成。
 * 如果自己来管理这些任务，逻辑可能会非常复杂，若API使用不恰当，可能会消耗大量电量
 *
 * 2.保证任务一定会被执行
 * WorkManager能保证任务一定会被执行，即使应用程序当前不在运行中，甚至在设备重启过后，任务仍然会在适当的时刻被执行。
 * 这是因为WorkManager有自己的数据库，关于任务的所有信息和数据都保存该数据库中。因此，只要任务交给了WorkManager，
 * 哪怕应用程序彻底退出，或者设备被重新启动，WorkManager依然能够保证完成你交给它的任务
 *
 * 3.兼容范围广
 * WorkManager最低能兼容APILevel14。
 *
 * 总结：在某些非原生系统中WorkManager无法正常使用，因为可能非原生系统不允许AlarmManager被主动唤起
 *
 * @author JinXin
 */
public class WorkManagerTestActivity extends AppCompatActivity {

    private static final String TAG = "WorkManagerTestActivity";
    private OneTimeWorkRequest uploadWorkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_test);

        findViewById(R.id.btn_start_ont_time_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOneTimeTask();
            }
        });

        findViewById(R.id.btn_cancel_ont_time_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消任务
                WorkManager.getInstance(WorkManagerTestActivity.this).cancelWorkById(uploadWorkRequest.getId());
            }
        });

    }

    private void startOneTimeTask() {

        // 使用WorkRequest配置任务
        // 配置任务就是告诉系统，任务何时运行及如何运行
        // 设置任务出发条件
        // 例如，当设置处于充电时，网络已连接，且电池电量充足的状态下，才触发任务
        Constraints constraints = new Constraints.Builder()
                // 充电时执行
                .setRequiresCharging(true)
                // 网络连接
                .setRequiredNetworkType(NetworkType.CONNECTED)
                // 在电池充足时执行
                .setRequiresBatteryNotLow(true)
                .build();
        Log.d(TAG, "startOneTimeTask: 使用WorkRequest配置任务");

        // WorkManager与Work之间的参数传递
        // WorkManager 通过 setInputData() 方法向 Worker 传递数据。数据的传递通过Data对象来完成。
        // 需要注意的是，Data只能用于传递一些小的基本类型的数据且数据最大不能超过 10KB。
        Data inputData = new Data.Builder()
                .putString("inputData", "Hello Word!")
                .build();

        // 将任务触发条件设置到WorkRequest
        // WorkRequest是一个抽象类，它有两种实现方式————OneTimeWorkRequest和PeriodicWorkRequest，
        // 分别对应的是一次性任务和周期性任务
        uploadWorkRequest = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class)
                // 设置触发条件
                .setConstraints(constraints)
                // 设置延迟任务
                // 假设没有设置触发条件，或者所设置的触发条件此刻符合系统的执行要求，此时，系统有可能会立刻执行该任务
                // 如果需要延迟任务的执行，可以通过setInitialDelay()方法执行，对任务进行延后
                .setInitialDelay(0, TimeUnit.SECONDS)
                // 设置指数退避策略
                // 假如Worker线程的执行出现了问题，如服务器宕机。你可能希望过一段时间后，重试该任务。
                // 那么可以在Worker的doWork()方法中返回Result.retry()，系统会有默认的指数退避策略来帮你重试任务，
                // 也可以通过setBackoffCriteria()方法来自定义指数退避策略
                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                // 为任务设置标签
                // 设置tag标签后,你就可以通过该标签跟踪任务的状态：WorkManager.getWorkInfoByTagLiveData(String tag)
                // 也可以取消任务： WorkManager.cancelAllWorkByTag(String tag)
                .addTag("UploadTag")
                // 传递数据
                .setInputData(inputData)
                .build();
        Log.d(TAG, "startOneTimeTask: 将任务触发条件设置到WorkRequest");

        // 观察任务的状态
        // 任务在提交给系统后，可以通过WorkInfo获知任务的状态。WorkInfo包含任务的id、tag、Worker对象传递过来的outputData,以及任务当前的状态
        // 有3种方式可以得到WorkInfo对象：getWorkInfosByTag()、getWorkInfoById()、getWorkInfosForUniqueWork()
        // 如果希望实时获知任务的状态，这3个方法还有对应的LiveData方法
        // getWorkInfosByTagLiveData、getWorkInfoByIdLiveData、getWorkInfosForUniqueWorkLiveData
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {

                        if (workInfo != null && workInfo.getState() == WorkInfo.State.ENQUEUED) {
                            Log.d(TAG, "onChanged: " + workInfo.getState());
                        }

                        if (workInfo != null && workInfo.getState() == WorkInfo.State.RUNNING) {
                            Log.d(TAG, "onChanged: " + workInfo.getState());
                        }

                        if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            String outputData = workInfo.getOutputData().getString("outputData");
                            Log.d(TAG, "onChanged: " + outputData);
                        }
                    }
                });

        Log.d(TAG, "startOneTimeTask: 执行任务");
        // 任务配置好之后，提交给系统执行
        WorkManager.getInstance(WorkManagerTestActivity.this).enqueue(uploadWorkRequest);

    }

    private void startPeriodicTask() {

        // 使用WorkRequest配置任务
        // 配置任务就是告诉系统，任务何时运行及如何运行
        // 设置任务出发条件
        // 例如，当设置处于充电时，网络已连接，且电池电量充足的状态下，才触发任务
        Constraints constraints = new Constraints.Builder()
                // 充电时执行
                .setRequiresCharging(true)
                // 网络连接
                .setRequiredNetworkType(NetworkType.CONNECTED)
                // 在电池充足时执行
                .setRequiresBatteryNotLow(true)
                .build();
        Log.d(TAG, "startPeriodicTask: 使用WorkRequest配置任务");

        // 周期性任务
        // 周期性任务的间隔时间不能少于15分钟
        PeriodicWorkRequest uploadWorkRequest = new PeriodicWorkRequest.Builder(UpLoadLogWorker.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .addTag("PeriodicUploadTag")
                .build();

    }

    private void taskChain() {

        // 压缩任务
        OneTimeWorkRequest compressWorkRequest = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class).build();
        // 上传任务
        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class).build();
        // 更新本地数据
        OneTimeWorkRequest uploadLocalWorkRequest = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class).build();

        // 任务链
        // 如果有一系列的任务需要按顺序执行，那么可以利用WorkManager.beginWith().then().then()...enqueue()的方式构建任务链
        // 例如：在上传数据之前，可能需要先对数据进行压缩
        WorkManager.getInstance(WorkManagerTestActivity.this)
                .beginWith(compressWorkRequest)
                .then(uploadWorkRequest)
                .enqueue();

        List<OneTimeWorkRequest> oneTimeWorkRequestList = new ArrayList<>();
        oneTimeWorkRequestList.add(compressWorkRequest);
        oneTimeWorkRequestList.add(uploadLocalWorkRequest);
        // 例如：压缩数据和更新本地数据二者同时进行，但与上传数据存在先后顺序
        WorkManager.getInstance(WorkManagerTestActivity.this)
                .beginWith(oneTimeWorkRequestList)
                .then(uploadWorkRequest)
                .enqueue();

        // 更复杂的任务链 使用WorkContinuation.combine()方法，将任务链组合起来使用
        OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class).build();
        OneTimeWorkRequest workRequest2 = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class).build();
        OneTimeWorkRequest workRequest3 = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class).build();
        OneTimeWorkRequest workRequest4 = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class).build();
        OneTimeWorkRequest workRequest5 = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class).build();

        WorkContinuation workContinuation1 = WorkManager.getInstance(WorkManagerTestActivity.this).beginWith(workRequest1).then(workRequest2);
        WorkContinuation workContinuation2 = WorkManager.getInstance(WorkManagerTestActivity.this).beginWith(workRequest3).then(workRequest4);
        List<WorkContinuation> workContinuationList = new ArrayList<>();
        workContinuationList.add(workContinuation1);
        workContinuationList.add(workContinuation2);

        // 任务的执行顺序为 1、2、3、4、5
        WorkContinuation.combine(workContinuationList)
                .then(workRequest5)
                .enqueue();
    }
}
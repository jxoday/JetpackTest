package com.jinxin.jetpacktest.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;

import com.jinxin.jetpacktest.R;

import java.util.concurrent.TimeUnit;

/**
 * @author JinXin
 */
public class WorkManagerTestActivity extends AppCompatActivity {

    private static final String TAG = "WorkManagerTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_test);

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

        // 将任务触发条件设置到WorkRequest
        // WorkRequest是一个抽象类，它有两种实现方式————OneTimeWorkRequest和PeriodicWorkRequest，
        // 分别对应的是一次性任务和周期性任务
        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UpLoadLogWorker.class)
                // 设置触发条件
                .setConstraints(constraints)
                // 设置延迟任务
                // 假设没有设置触发条件，或者所设置的触发条件此刻符合系统的执行要求，此时，系统有可能会立刻执行该任务
                // 如果需要延迟任务的执行，可以通过setInitialDelay()方法执行，对任务进行延后
                .setInitialDelay(10, TimeUnit.SECONDS)
                // 设置指数退避策略
                // 假如Worker线程的执行出现了问题，如服务器宕机。你可能希望过一段时间后，重试该任务。
                // 那么可以在Worker的doWork()方法中返回Result.retry()，系统会有默认的指数退避策略来帮你重试任务，
                // 也可以通过setBackoffCriteria()方法来自定义指数退避策略
                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                // 为任务设置标签
                // 设置tag标签后,你就可以通过该标签跟踪任务的状态：WorkManager.getWorkInfoByTagLiveData(String tag)
                // 也可以取消任务： WorkManager.cancelAllWorkByTag(String tag)
                .addTag("UploadTag")
                .build();

        Log.d(TAG, "onCreate: 准备执行");
        // 任务配置好之后，提交给系统执行
        WorkManager.getInstance(this).enqueue(uploadWorkRequest);

        // 观察任务的状态
        // 任务在提交给系统后，可以通过WorkInfo获知任务的状态。WorkInfo包含任务的id、tag、Worker对象传递过来的outputData,以及任务当前的状态
        // 有3种方式可以得到WorkInfo对象：getWorkInfosByTag()、getWorkInfoById()、getWorkInfosForUniqueWork()
        // 如果希望实时获知任务的状态，这3个方法还有对应的LiveData方法
        // getWorkInfosByTagLiveData、getWorkInfoByIdLiveData、getWorkInfosForUniqueWorkLiveData
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        Log.d(TAG, "onChanged: " + workInfo.toString());
                    }
                });

    }
}
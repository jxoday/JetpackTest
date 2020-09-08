package com.jinxin.jetpacktest.room.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.jinxin.jetpacktest.room.MyDatabase;
import com.jinxin.jetpacktest.room.entity.Student;

import java.util.List;

/**
 * 查询任务
 * @author JinXin 2020/9/8
 */
public class QueryStudentTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "QueryStudentTask";

    private MyDatabase myDatabase;
    private List<Student> studentList;

    public QueryStudentTask(MyDatabase myDatabase, List<Student> studentList) {
        this.myDatabase = myDatabase;
        this.studentList = studentList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: 执行 线程任务前的操作");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: 接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果");
        studentList.clear();
        studentList.addAll(myDatabase.studentDao().getStudentList());
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: 在主线程 显示线程任务执行的进度");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG, "onPostExecute: 接收线程任务执行结果、将执行结果显示到UI组件");
        if (onTaskListener != null) {
            onTaskListener.onShowResult();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d(TAG, "onCancelled: 将异步任务设置为：取消状态");
    }

    public interface OnTaskListener {

        /**
         * 显示结果
         */
        void onShowResult();
    }
    private OnTaskListener onTaskListener;
    public void setOnTaskListener(OnTaskListener onTaskListener) {
        this.onTaskListener = onTaskListener;
    }
}

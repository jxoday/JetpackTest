package com.jinxin.jetpacktest.room.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.jinxin.jetpacktest.room.MyDatabase;
import com.jinxin.jetpacktest.room.entity.Student;

import java.util.List;

/**
 * 插入任务
 *
 * @author JinXin 2020/9/8
 */
public class InsertStudentTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "InsertStudentTask";

    String name;
    String age;
    List<Student> studentList;
    MyDatabase myDatabase;

    public InsertStudentTask(final String name, final String age, List<Student> studentList, MyDatabase myDatabase) {
        this.name = name;
        this.age = age;
        this.studentList = studentList;
        this.myDatabase = myDatabase;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        myDatabase.studentDao().insertStudent(new Student(name, age));
        studentList.clear();
        studentList.addAll(myDatabase.studentDao().getStudentList());
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Log.d(TAG, "onPostExecute: ");
        if (onTaskListener != null) {
            onTaskListener.onShowResult();
        }
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

package com.jinxin.jetpacktest.room.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.jinxin.jetpacktest.room.MyDatabase;
import com.jinxin.jetpacktest.room.entity.Student;

import java.util.List;

/**
 * 删除任务
 *
 * @author JinXin 2020/9/8
 */
public class DeleteStudentTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "DeleteStudentTask";

    Student student;
    List<Student> studentList;
    MyDatabase myDatabase;

    public DeleteStudentTask(Student student, List<Student> studentList, MyDatabase myDatabase) {
        this.student = student;
        this.studentList = studentList;
        this.myDatabase = myDatabase;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        myDatabase.studentDao().deleteStudent(student);
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

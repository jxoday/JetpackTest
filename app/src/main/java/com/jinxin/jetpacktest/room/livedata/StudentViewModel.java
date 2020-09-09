package com.jinxin.jetpacktest.room.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jinxin.jetpacktest.room.MyDatabase;
import com.jinxin.jetpacktest.room.asynctask.DeleteStudentTask;
import com.jinxin.jetpacktest.room.asynctask.InsertStudentTask;
import com.jinxin.jetpacktest.room.asynctask.UpdateStudentTask;
import com.jinxin.jetpacktest.room.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JinXin 2020/9/9
 */
public class StudentViewModel extends AndroidViewModel {

    private MyDatabase myDatabase;
    private LiveData<List<Student>> liveDataStudent;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        myDatabase = MyDatabase.getDatabase(application);
        liveDataStudent = myDatabase.studentDao().getLiveStudentList();
    }

    public LiveData<List<Student>> getLiveDataStudent() {
        return liveDataStudent;
    }

    public void addStudent(String name, String age) {
        List<Student> studentList = new ArrayList<>();
        InsertStudentTask insertStudentTask = new InsertStudentTask(name, age, studentList, myDatabase);
        insertStudentTask.execute();
    }

    public void deleteStudent(Student student) {
        List<Student> studentList = new ArrayList<>();
        DeleteStudentTask deleteStudentTask = new DeleteStudentTask(student, studentList, myDatabase);
        deleteStudentTask.execute();
    }

    public void updateStudent(Student student, String name, String age) {
        List<Student> studentList = new ArrayList<>();
        UpdateStudentTask updateStudentTask = new UpdateStudentTask(student.id, name, age, studentList, myDatabase);
        updateStudentTask.execute();
    }
}

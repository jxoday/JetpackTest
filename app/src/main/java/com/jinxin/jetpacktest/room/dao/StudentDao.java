package com.jinxin.jetpacktest.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jinxin.jetpacktest.room.entity.Student;

import java.util.List;

/**
 * @author JinXin 2020/9/7
 */
@Dao
public interface StudentDao {

    /**
     * 新增
     * @param student 学生对象
     */
    @Insert
    void insertStudent(Student student);

    /**
     * 删除
     * @param student 学生对象
     */
    @Delete
    void deleteStudent(Student student);

    /**
     * 更新
     * @param student 学生
     */
    @Update
    void updateStudent(Student student);

    /**
     * 获取获取学生数据
     * @return
     */
    @Query("select * from student")
    List<Student> getStudentList();

    /**
     * 根据id获取学生数据
     * @param id id
     * @return
     */
    @Query("select * from student where id = :id")
    Student getStudentById(int id);

}

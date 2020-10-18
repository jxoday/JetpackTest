package com.jinxin.mvvm.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jinxin.mvvm.model.User;

/**
 * @author JinXin 2020/10/18
 */
@Dao
public interface UserDao {

    /**
     * 新增用户
     * @param user 用户
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    /**
     * 删除用户
     * @param user 用户
     */
    @Delete
    void deleteStudent(User user);

    /**
     * 根据名称查找用户信息
     * @param name 用户名称
     * @return 用户信息
     */
    @Query("select * from user where name = :name")
    LiveData<User> getUserByName(String name);
}

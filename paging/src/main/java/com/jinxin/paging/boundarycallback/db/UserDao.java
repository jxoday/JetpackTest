package com.jinxin.paging.boundarycallback.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jinxin.paging.boundarycallback.model.User;

import java.util.List;

/**
 * @author JinXin 2020/10/11
 */
@Dao
public interface UserDao {

    /**
     * 批量插入用户
     * @param users 用户列表
     */
    @Insert
    void insertUsers(List<User> users);

    /**
     *  删除表
     */
    @Query("delete from user")
    void clear();

    /**
     * 获取用户数据
     * @return 用户列表数据
     */
    @Query("select * from user")
    DataSource.Factory<Integer, User> getUserList();
}

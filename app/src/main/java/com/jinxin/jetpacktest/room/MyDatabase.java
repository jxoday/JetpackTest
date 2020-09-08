package com.jinxin.jetpacktest.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jinxin.jetpacktest.room.dao.StudentDao;
import com.jinxin.jetpacktest.room.entity.Student;

/**
 * Database 标签用于告诉系统这个Room数据对象。entities属性用于指定该数据库有哪些表，若需要建立多张表，
 * 则表名以逗号相隔开。version属性用于指定数据库版本号，后面数据库的升级正是依据版本号进行判断的。
 * 数据库类需要继承自RoomDatabase，通过Room.databaseBuilder()结合单例设计模式完成创建。
 *
 * @author JinXin 2020/9/7
 */
@Database(entities = {
        Student.class
}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "my_db";

    private static MyDatabase database;

    public static synchronized MyDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MyDatabase.class,
                    DATABASE_NAME)
                    .build();
        }
        return database;
    }

    /**
     * Student数据操作
     * @return studentDao
     */
    public abstract StudentDao studentDao();
}

package com.jinxin.mvvm.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jinxin.mvvm.model.User;

/**
 * @author JinXin 2020/10/18
 */
@Database(entities = {
        User.class
}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static final String TAG = "UserDatabase";

    public static final String DATABASE_NAME = "user_db";

    private static UserDatabase databaseInstance;

    public static synchronized UserDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class,
                    DATABASE_NAME)
                    .build();
        }
        return databaseInstance;
    }

    /**
     * 用户数据库操作
     * @return userDao
     */
    public abstract UserDao userDao();
}

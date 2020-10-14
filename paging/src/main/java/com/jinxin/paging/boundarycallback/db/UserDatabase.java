package com.jinxin.paging.boundarycallback.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.jinxin.paging.boundarycallback.model.User;

/**
 * @author JinXin 2020/10/11
 */
@Database(entities = {
        User.class
}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "user_db";

    private static UserDatabase database;

    public static synchronized UserDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserDatabase.class,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration() // 在出现升级异常时，重新创建数据表
                    .build();
        }
        return database;
    }

    public abstract UserDao userDao();
}

package com.jinxin.jetpacktest.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
}, version = 3)
public abstract class MyDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "my_db";

    private static MyDatabase database;

    public static synchronized MyDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MyDatabase.class,
                    DATABASE_NAME)
                    // 从assets/database/students目录下读取students.db
                    .createFromAsset("database/students.db")
                    .fallbackToDestructiveMigration() // 在出现升级异常时，重新创建数据表
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build();
        }
        return database;
    }

    /**
     * Room升级
     */
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // 执行与升级相关的操作

        }
    };

    /**
     * 销毁与重建策略
     */
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            // 1.创建一个符合表结果要求的临时表temp_student
            database.execSQL("create table temp_student (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name TEXT," +
                    "age INTEGER NOT NULL)");
            // 2.将数据从旧表student复制至临时表temp_student
            database.execSQL("insert into temp_student (id, name, age) " +
                    "select id, name, age from student");
            // 3.删除旧表student
            database.execSQL("drop table student");
            // 4.将临时表temp_student 重命名为 student
            database.execSQL("alter table temp_student rename to student");
        }
    };


    /**
     * Student数据操作
     * @return studentDao
     */
    public abstract StudentDao studentDao();
}

package com.jinxin.jetpacktest.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 学生实体类
 *
 * Entity 标签用于将Students类与Room中的数据表对应起来。tableName属性可以为数据表设置表名，若不设置，则表明与类型相同
 * PrimaryKey 标签用于指定该字段作为表的主键
 * Ignore 标签用来告诉Room忽略该字段或方法
 *
 * @author JinXin 2020/9/7
 */
@Entity(tableName = "student")
public class Student {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    public int id;

    public String name;

    public String age;

    /**
     * Room默认会使用这个构造器操作数据
     * @param id id
     * @param name 名称
     * @param age 年龄
     */
    public Student(int id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * 由于Room只能识别和使用一个构造器，如果希望定义多个构造器
     * 可以使用Ignore标签，让Room忽略这个构造器
     * 不仅如此，Ignore标签还可用于字段
     * Room不会持久化被Ignore标签标记过的字段的数据
     * @param name 名称
     * @param age 年龄
     */
    @Ignore
    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }
}

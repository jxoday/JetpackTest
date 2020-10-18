package com.jinxin.mvvm.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author JinXin 2020/10/18
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    @SerializedName("id")
    public int id;

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    @SerializedName("name")
    public String name;

    @ColumnInfo(name = "avatar", typeAffinity = ColumnInfo.TEXT)
    @SerializedName("avatar_url")
    public String avatar;

    @ColumnInfo(name = "followers", typeAffinity = ColumnInfo.INTEGER)
    @SerializedName("followers")
    public int followers;

    @ColumnInfo(name = "following", typeAffinity = ColumnInfo.INTEGER)
    @SerializedName("following")
    public int following;

    @ColumnInfo(name = "blog", typeAffinity = ColumnInfo.TEXT)
    @SerializedName("blog")
    public String blog;

    @ColumnInfo(name = "company", typeAffinity = ColumnInfo.TEXT)
    @SerializedName("company")
    public String company;

    @ColumnInfo(name = "bio;", typeAffinity = ColumnInfo.TEXT)
    @SerializedName("bio")
    public String bio;

    @ColumnInfo(name = "location;", typeAffinity = ColumnInfo.TEXT)
    @SerializedName("location")
    public String location;

    @ColumnInfo(name = "htmlUrl;", typeAffinity = ColumnInfo.TEXT)
    @SerializedName("html_url")
    public String htmlUrl;
}

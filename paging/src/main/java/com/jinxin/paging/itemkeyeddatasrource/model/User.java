package com.jinxin.paging.itemkeyeddatasrource.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author JinXin 2020/10/11
 */
@Data
@AllArgsConstructor
public class User {

    @SerializedName("account_id")
    public int id;

    @SerializedName("login")
    public String name;

    @SerializedName("avatar_url")
    public String avatar;
}

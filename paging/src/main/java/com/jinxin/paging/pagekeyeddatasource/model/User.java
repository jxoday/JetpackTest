package com.jinxin.paging.pagekeyeddatasource.model;

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

    @SerializedName("display_name")
    public String name;

    @SerializedName("profile_image")
    public String avatar;
}

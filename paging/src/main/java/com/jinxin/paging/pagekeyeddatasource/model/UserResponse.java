package com.jinxin.paging.pagekeyeddatasource.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * @author JinXin 2020/10/11
 */
@Data
public class UserResponse {

    @SerializedName("items")
    public List<User> users;

    @SerializedName("has_more")
    public Boolean hasMore;

}

package com.jinxin.paging.positionaldatasource.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * @author JinXin 2020/10/9
 */
@Data
public class Movies {

    /**
     * 当前返回的数量
     */
    public int count;

    /**
     * 起始位置
     */
    public int start;

    /**
     * 一共多少数据
     */
    public int total;

    /**
     * 电影列表
     */
    @SerializedName("subjects")
    public List<Movie> movieList;
}

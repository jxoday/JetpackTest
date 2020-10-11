package com.jinxin.paging.positionaldatasource.model;

import lombok.Data;

/**
 * @author JinXin 2020/10/9
 */
@Data
public class Movie {

    public String id;

    public String title;

    public String year;

    public Image images;

    @Data
    public static class Image {

        public String large;
    }
}

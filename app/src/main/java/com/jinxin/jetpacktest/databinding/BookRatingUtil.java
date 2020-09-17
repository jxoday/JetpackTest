package com.jinxin.jetpacktest.databinding;

/**
 * @author JinXin 2020/9/17
 */
public class BookRatingUtil {

    public static String getRatingString(int rating) {
        switch (rating) {
            case 0:
                return "0";
            case 1:
                return "1";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            default:
                break;
        }
        return "未知";
    }

}

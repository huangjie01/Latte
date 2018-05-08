package com.example.latte_core.utils;


import com.example.latte_core.app.Latte;

/**
 * Created by huilianyi on 2016/10/12.
 */

public class StringUtils {
    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }


    public static String getString(int id) {
        return Latte.getApplicationContext().getString(id);
    }
}

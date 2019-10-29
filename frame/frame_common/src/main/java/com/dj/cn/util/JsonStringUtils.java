package com.dj.cn.util;

/**
 * @version V1.0
 * @Title: JsonStringUtils
 * @Package: com.dj.cn.util
 * @author: Lenovo
 * @date: 2019/7/22 11:21
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class JsonStringUtils {
    public JsonStringUtils() {
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0 || value.trim().length() == 0;
    }

    public static boolean isNotEmpty(String value) {
        return !isNullOrEmpty(value);
    }

    public static String concat(String... str) {
        if (str.length == 0) {
            return null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();

            for(int i = 0; i < str.length; ++i) {
                stringBuffer.append(str[i]);
            }

            return stringBuffer.toString();
        }
    }

    public static String upperFirstCase(String value) {
        char[] array = value.toCharArray();
        if (array[0] >= 'a' && array[0] <= 'z') {
            array[0] = (char)(array[0] - 32);
        }

        return String.valueOf(array);
    }

    public static String trimEnd(String value, String tail) {
        if (value != null && tail != null) {
            int index = value.indexOf(tail);
            return index == value.length() - tail.length() ? value.substring(0, index) : value;
        } else {
            return value;
        }
    }
}

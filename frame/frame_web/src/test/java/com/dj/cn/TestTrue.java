package com.dj.cn;

import com.dj.cn.util.StringUtils;

/**
 * @version V1.0
 * @Title: TestTrue
 * @Package: com.dj.cn
 * @author: Lenovo
 * @date: 2019/8/19 16:45
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class TestTrue {
    public static void main(String[] args) {

        String a = "qq";
        String s = a.toLowerCase();
        String replace = s.replace(" ", "");
        System.out.println(replace);

        test(a);
    }

    public static String test(String a) {
        if(StringUtils.isEmpty(a))
            return "false";
         return a;
    }
}
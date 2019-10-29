package com.dj.cn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version V1.0
 * @Title: test3
 * @Package: com.dj.cn
 * @author: Lenovo
 * @date: 2019/7/27 15:17
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class test3 {
    public static void main(String[] args) {
        String weekday = getWeekday("2019-07-27");
        System.out.println(1);
    }
   // 实现给定某日期，判断是星期几
    public static String getWeekday(String date){//必须yyyy-MM-dd
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdw = new SimpleDateFormat("E");
        Date d = null;
        try {
            d = sd.parse("2019-07-29");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdw.format(d);
    }
}

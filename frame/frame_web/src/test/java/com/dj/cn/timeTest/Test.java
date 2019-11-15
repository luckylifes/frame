package com.dj.cn.timeTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test
 * @Description TODO
 * @Author wj
 * @Date 2019/11/15 16:25
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        String a = "2019-11-15 07:42:05";
        String b = "2019-11-26 07:42:05";
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(b);
            long days = TimeUnit.MILLISECONDS.toDays(date2.getTime() - date1.getTime());
            System.out.println(days);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

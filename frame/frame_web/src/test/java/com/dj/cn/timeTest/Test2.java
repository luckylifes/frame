package com.dj.cn.timeTest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName Test2
 * @Description TODO
 * @Author wj
 * @Date 2019/11/18 14:10
 * @Version 1.0
 **/
public class Test2 {
    public static void main(String[] args) {
        String timeZone = "UTC+5.53";
        String t = timeZone.substring(4, timeZone.length());
        BigDecimal minutes = new BigDecimal(t).multiply(new BigDecimal("60")).setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println(minutes);
        int i = minutes.intValue();
        int timeCount = 0;
            if (timeZone.contains("+")) {
                timeCount = i;
            } else if (timeZone.contains("-")) {
                timeCount = -i;
            }

            Calendar c = Calendar.getInstance();
            c.setTime( new Date());
            c.add(Calendar.MINUTE, timeCount);
        System.out.println(new Date());
        System.out.println(c.getTime());
        System.out.println(1);
            //partOrder.setCreated(c.getTime());

    }
}

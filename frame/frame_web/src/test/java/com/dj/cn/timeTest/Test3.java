package com.dj.cn.timeTest;

import com.dj.cn.util.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @ClassName Test3
 * @Description TODO
 * @Author wj
 * @Date 2019/11/19 19:07
 * @Version 1.0
 **/
public class Test3 {
//    public void parseTime (PartOrder partOrder) {
//        String timeZone = partOrder.getTimeZone();
//        if (commercial.base.common.util.StringUtils.isNoneEmpty(timeZone) && timeZone.contains("UTC")) {
//            String t = timeZone.substring(4, timeZone.length());
//            int i = new BigDecimal(t).multiply(new BigDecimal("60")).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
//            Integer timeCount = 0;
//            if (timeZone.contains("+")) {
//                timeCount = i;
//            } else if (timeZone.contains("-")) {
//                timeCount = -i;
//            }
//            Calendar c = Calendar.getInstance();
//            c.setTime(partOrder.getCreated());
//            c.add(Calendar.MINUTE, timeCount);
//            partOrder.setCreated(c.getTime());
//        }
//    }
    public static void main(String[] args) {
        String timeZone = "UTC-1.53";
        if (StringUtils.isNotEmpty(timeZone) &&  timeZone.contains("UTC")) {
            String t = timeZone.substring(4, timeZone.length());
            String zooe = "";
            if (t.contains(".")) {
                String[] split = t.split("\\.");
                String h = split[0];
                String m = split[1];
                String m1 = "0."+m;
                BigDecimal decimal = new BigDecimal(m1).multiply(new BigDecimal("60")).setScale(0, BigDecimal.ROUND_HALF_UP);
                String string = decimal.toString();
                if (h.length() == 1) {
                    h = "0"+h;
                }
                zooe = h+":"+string;
            } else {
                if (t.length() == 1) {
                    zooe = "0"+t+":"+"00";
                } else {
                    zooe = t+":"+"00";
                }
            }
            if (timeZone.contains("+")) {
                timeZone = "UTC+"+zooe;
            } else {
                timeZone = "UTC-"+zooe;
            }
            System.out.println(timeZone);
        }
    }
}

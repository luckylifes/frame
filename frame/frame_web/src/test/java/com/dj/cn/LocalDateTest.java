package com.dj.cn;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @version V1.0
 * @Title: LocalDateTest
 * @Package: com.dj.cn
 * @author: Lenovo
 * @date: 2019/8/5 17:29
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        int dayOfMonth1 = localDate.getDayOfMonth();
        Month month = localDate.getMonth();
        int dayOfMonth = localDate.getDayOfMonth();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfYear = localDate.getDayOfYear();


        LocalDate localDate1 = localDate.minusDays(2);
        LocalDate localDate2 = localDate.minusYears(1);


        LocalDate date = localDate.plusDays(4);
        localDate.plusWeeks(1);



    }
}

package com.dj.cn;

import com.dj.cn.domain.entry.User;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version V1.0
 * @Title: Test1
 * @Package: com.dj.cn
 * @author: Lenovo
 * @date: 2019/6/26 14:39
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class Test1 {
    /* public static void main(String[] args) {
     *//* String a ="";
               a = StringUtils.isEmpty(a)? "sss" : a;
        System.out.println(a);*//*

        try {
            String a = "2011-02-03 12:02:03.0";
                String substring = a.substring(0, 10);
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(a);
                Format f = new SimpleDateFormat("dd/MM/yyyy");
               // order.setOrderEntryDate(f.format(date.getTime()));
            System.out.println(f.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/


    /* public static void main(String[] args) {

     *//* if ("1".equals("1") && (1==2 || 2==2)) {
            System.out.println(true);
        }*//*
    }*/


    //private static Comparator comparator= Collator.getInstance(java.util.Locale.CHINA);
  /*  public static void main(String[] args) {
        List<String> personList= Arrays.asList("ahjoohh","dffdgh","trutytu","cbnjhk","abdgrht");
        Collections.sort(personList,Collator.getInstance(java.util.Locale.CHINA));
        personList.forEach(s -> {
            System.out.println(s);
        });

    }*/


    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        user1.setUsername("rtwerty");
        user1.setId(1);
        user2.setUsername("vbsdfwerty");
        user2.setId(5);
        user3.setUsername("aberty");
        user3.setId(3);
        user4.setUsername("aswerty");
        user4.setId(6);
        list.add(user1);
        list.add(user2);
        list.add(user4);
        list.add(user3);
        list.sort(Comparator.comparing(user -> user.getUsername()));
        list.forEach(user -> {
            System.out.println(user.getUsername());
            //log.info(user.getUsername());
        });
    }


  /*  public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //String s = list.get(0);
        //System.out.println(s);
    }*/


    /*public static void main(String[] args) {
        User user = new User();
        String username = user.getUsername();
    }*/

}

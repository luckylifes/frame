package com.dj.cn;

import com.dj.cn.domain.entry.User;

import java.util.Date;

/**
 * @version V1.0
 * @Title: Test4
 * @Package: com.dj.cn
 * @author: Lenovo
 * @date: 2019/8/15 14:33
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class Test4 {

    public static void main(String[] args) {
        /**
         * 用new关键字创建
         */
        User user = new User();

        /**
         * 利用反射，调用Class类的或者是Constructor类的newInstance（）方法
         */
        try {
            User user1 = User.class.newInstance();
            user1.setId(1);
            System.out.println(user1.getId());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
   /* public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, CloneNotSupportedException {
        User user = (User) Class.forName("com.dj.cn.domain.entry.User").newInstance();
        user.setUsername("zs");
        System.out.println(user);

        User user1 = new User(1, "li",123, "11", 2, new Date(), "1",2,"11", "@22", "22");
        System.out.println(user1);

        User user2 = (User) user1.clone();
        System.out.println(user2);

        System.out.println(user1 == user2);
        System.out.println(user1.equals(user2));


        System.out.println(user.equals(user1));
    }
*/











}

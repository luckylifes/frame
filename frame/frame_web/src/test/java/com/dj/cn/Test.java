package com.dj.cn;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.crypto.hash.Hash;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Title: Test
 * @Package: com.dj.cn
 * @author: Lenovo
 * @date: 2019/6/20 14:37
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class Test {

    public static void main(String[] args) {
        boolean a = true;
      // boolean a = false;
       if (a) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

      //  System.out.println(a ? "04" : "03");

        String aa = "03";
        //String aa = "04";

        //
       if (aa.contains("04") ? true : false) {
           System.out.println("111");
       }else {
           System.out.println("222");
       }

   Boolean bb =aa.contains("04") ? true : false;
        System.out.println(11);





        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Integer i = 1;
        for (Integer integer : list) {
            System.out.println(10*i);
            i++;
        }






    }


    Map<String, String> map = new HashMap<>();
    JSONObject j  = new JSONObject();

}

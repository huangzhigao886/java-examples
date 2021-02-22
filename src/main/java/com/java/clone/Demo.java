package com.java.clone;

import com.model.Apple;
import com.model.Tiger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/16
 * @Description:
 */
public class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
//        Tiger tiger = new Tiger("male",12);
//        Tiger demo1 = (Tiger) tiger.clone();
//        demo1.setAge(13);
//        demo1.setName("female");
//        System.out.println(tiger == demo1);
//        System.out.println(tiger.hashCode());
//        System.out.println(demo1.hashCode());
//        System.out.println(tiger.equals(demo1));
//        System.out.println(tiger);
//        System.out.println(demo1);
//            Date date = new Date();
//
//        System.out.println((Date)date);

        Map<String,String> map = new HashMap<>();
        map.put("aa","aa");
        map.remove("aa");
    }
}

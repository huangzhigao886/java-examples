package com.util;

import java.util.HashMap;

/**
 * @description: hashmap的使用
 * @author: hzg
 * @create: 2019-09-11 09:25
 **/

public class HashMapValue {
    private HashMap<String ,Integer > map;

    public void test (){
        map = new HashMap<>();
        for( int i = 0;i<5;i++){
            map.put("a"+i,i);
        }
    }

    public void test1(){
        System.out.println(map.get("a1"));
    }

    public static void main(String[] args) {
        HashMapValue mm = new HashMapValue();
        mm.test();
        mm.test1();
    }
}

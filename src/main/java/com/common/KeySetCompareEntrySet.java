package com.common;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: keySet与EntrySet的性能比较
 * @author: hzg
 * @create: 2019-09-06 14:32
 **/

public class KeySetCompareEntrySet {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("A", "123");
        map.put("B", "234");
        System.out.println("KeySet输出的值");
//        for (String key : map.keySet()) {
////            str1 =  "${" + key + "}", map.get((String) key);
//            System.out.println("第一部分" + ":   " + "${" + key + "}");
//            System.out.println("第二部分" + ":   " + map.get((String) key));
//        }
//
//        System.out.println("EntrySet输出的值");
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println("第一部分" + ":   " + "${" + entry.getKey() + "}");
//            System.out.println("第二部分" + ":   " + entry.getValue());
//        }

        String IP = "10：92221111：112221：23";
        String[] split = IP.split("：");
        System.out.println(Integer.parseInt(split[1]));
        System.out.println(Integer.valueOf(split[1]));
        System.out.println(IP.getBytes("UTF-8"));
        byte[] tmp = IP.getBytes("UTF-8");
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(tmp[i]);
        }
        System.out.println(tmp.toString());
        Object obj = new Object();
        System.out.println(obj == Boolean.TRUE);
    }
}

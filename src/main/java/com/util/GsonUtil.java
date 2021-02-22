package com.util;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Gson工具类
 * @author: hzg
 * @create: 2019-08-30 10:37
 **/

public class GsonUtil {
    public static void main(String[] args) {
        Gson gson = new Gson();
//        int i = gson.fromJson("100",int.class);
//        System.out.println(i);
//        double d = gson.fromJson("\"99.99\"", double.class);  //99.99
//        boolean b = gson.fromJson("true", boolean.class);     // true
//        String str = gson.fromJson("String", String.class);   // String
//        System.out.println(d+"  "+b+"  "+str);
//
//        Student student = new Student(1l,"huang",12,"aaa");
//        String studentJson = gson.toJson(student);
//        System.out.println(studentJson);
//        Student studentTest = gson.fromJson(studentJson,Student.class);
//        System.out.println(studentTest);
//
//
//
//        //GsonBuilder从名上也能知道是用于构建Gson实例的一个类，要想改变Gson默认的设置必须使用该类配置Gson
//        Gson gson1 = new GsonBuilder().serializeNulls().create();
//        Student student1 = new Student("nan",1);
//        String arg_is_null = gson1.toJson(student1);
//        System.out.println(arg_is_null);
        Map<String,String> map = new HashMap<>();
        map.put("aa","bb,d");
        map.put("cc","ee,d");
        String s = JSONUtil.toJsonStr(map);
        Map<String,String> map1 = gson.fromJson(s, Map.class);
        System.out.println(map1.size());
    }

}

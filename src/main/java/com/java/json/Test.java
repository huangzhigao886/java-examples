package com.java.json;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.java.json.dto.Apple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/27
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
//        List<Apple> appleList = new ArrayList<>();
//        appleList.add(new Apple("yellow","12"));
//        appleList.add(new Apple("red","12"));
//        String jsonString = JSONObject.toJSONString(appleList);
//        System.out.println(jsonString);
//
//
//
//        List<Map<String,Object>> list = new ArrayList<>();
//        Map<String,Object> map = new HashMap<>();
//        map.put("name","huang");
//        map.put("age",1);
//        list.add(map);
//        Map<String,Object> map1 = new HashMap<>();
//        map1.put("name","zhang");
//        map1.put("age",12);
//        list.add(map1);
//        String string = JSONObject.toJSONString(list);
//        System.out.println(string);

        Map<String, String> map2 = new HashMap<>();
        map2.put("color", "aa");
        map2.put("weight", "sda");
        map2.put("bb","ads");
        String string1 = JSONObject.toJSONString(map2);
        JSONObject jsonObject = JSON.parseObject(string1);
        Apple apple = JSONObject.parseObject(jsonObject.toJSONString(), Apple.class);
        System.out.println(apple);

//        List<Apple> lists = JSONArray.parseArray(jsonString, Apple.class);
//        for(Apple apple:lists){
//            System.out.println(apple);
//        }
    }
}

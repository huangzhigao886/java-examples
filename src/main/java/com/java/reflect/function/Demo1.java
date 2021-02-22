package com.java.reflect.function;

import cn.hutool.json.JSONUtil;
import com.java.reflect.AnnotationDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/11
 * @Description:
 */
@AnnotationDemo
public class Demo1 {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("cc", "dd");
        map.put("dd", 12);
        System.out.println(JSONUtil.toJsonStr(map));
    }

    public static void denglu() {

    }
}

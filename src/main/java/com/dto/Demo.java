package com.dto;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.exception.DemoException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Auther: huangzhigao
 * @Date: 2020/1/15
 * @Description:
 */
@Slf4j
public class Demo {
    public static void main(String[] args) {

        int a[] = {-6, 12, 1, 24, 3, 5};

        int j = 0, h = 0;
        int[] e = new int[a.length];
        int[] o = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0)
                e[j++] = a[i];
            else
                o[h++] = a[i];
        }

        for (int i = 1; i < e.length; i++) {
            if (e[i] >= e[j] && o[i] >= o[j]) {
                j++;


            } else {

            }
        }
    }




        @Test
    public  void test(){
        Map<String,Object> map = new HashMap<>();
        map.put("10179",new ArrayList<>());
        String str = JSONUtil.toJsonStr(map);
        System.out.println(str);
        JSONUtil.toBean(str,Map.class);
    }


    public static void testValue() {
        double a = 1000.0;
        int c = 1000;
        int d = 1000;
        Integer f = 1000;
        Integer h = 1000;
        String ss = "ccc";
        System.out.println(ss == "ccc");
        System.out.println(f == 1000);
        System.out.println(c == d);
        System.out.println(a == c);
    }


    public static Object ts() {
        Object i = null;
        try {
            i = 1 / 1;
            return "success";
        } catch (Exception e) {
            return "error";
        } finally {
            if (i == null) {
                return "aa";
            } else {
                return "bb";
            }
        }
    }
}

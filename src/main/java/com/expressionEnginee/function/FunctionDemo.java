package com.expressionEnginee.function;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2020/2/25
 * @Description:
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Map<String, Object> env = new HashMap<>();
        List<Object> list = new ArrayList<>();
        list.add(12);
        Map<String, Object> map = new HashMap<>();
        map.put("key", "zhangsan");
        env.put("time", "2019-12-13 12:12:21");
        env.put("map", map);
        env.put("list", list);

        env.put("key", "key");
//        env.put("name",13);
//        System.out.println(AviatorEvaluator.execute("date_to_string(time,'yyyy-MM-dd HH:mm:ss')",env));

//        System.out.println(AviatorEvaluator.execute("seq.contains_key(map,key)", env));

        System.out.println(AviatorEvaluator.execute("string.length(name)", env));


//        System.out.println();
    }
}

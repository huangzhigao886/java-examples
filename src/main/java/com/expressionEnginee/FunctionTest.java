package com.expressionEnginee;

import com.expressionEnginee.function.AddFunction;
import com.expressionEnginee.function.DecodeFunction;
import com.googlecode.aviator.AviatorEvaluator;
import com.model.SourceDef;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对自定的funtion进行测试
 */
@Slf4j
public class FunctionTest {
    public static void main(String[] args) {
        AviatorEvaluator.addFunction(new AddFunction());
        AviatorEvaluator.addFunction(new DecodeFunction());
        List<Integer> list= new ArrayList<>();
//        list.add("name");
//        list.add("huang");
//        list.add("A");
//        list.add("li");
//        list.add("B");
//        list.add("zhou");
//        list.add("C");
//        list.add("F");
//        list.add("4");

        list.add(5);
        Map<String,Object> env = new HashMap<>();
        env.put("list",list);
        env.put("name","li");
//        System.out.println(AviatorEvaluator.execute("decode(list,name)",env));
//        System.out.println(AviatorEvaluator.execute("add(1,2)"));

//        System.out.println(AviatorEvaluator.execute("100>1&&50<=100"));
        for(int i = 0 ;i<100;i++) {
            try {
                System.out.println(1 / 0);
            } catch (Exception e) {
                log.error("aaa");
            }
        }
        System.out.println("你好");
        System.out.println(AviatorEvaluator.execute("2>1"));
//        System.out.println(AviatorEvaluator.execute(" include (list,string.length('name'))",env));
    }




}

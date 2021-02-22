package com.java.ealystage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/** 方法引用
 * @Auther: huangzhigao
 * @Date: 2019/11/27
 * @Description:
 */

@Data
@AllArgsConstructor
class Apple{
    private String color;
    private int weight;
}

interface Predicate<T>{
    boolean test(T t);
}

public class MethodRef {
    public static boolean isGreanApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    public static boolean isHeaveyApple(Apple apple){
        return apple.getWeight()>150;
    }

    public static List<Apple> filterApple(List<Apple> appleList, Predicate<Apple> p){
        List<Apple> res = new ArrayList();
        for (Apple apple:appleList){
            if(p.test(apple)){
                res.add(apple);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Apple apple1 = new Apple("green",160);
        Apple apple2 = new Apple("huang",160);
        List<Apple> list = new ArrayList<>();
        list.add(apple1);
        list.add(apple2);
//        filterApple(list,Apple::isHeaveyApple);
    }

}

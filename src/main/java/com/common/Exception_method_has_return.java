package com.common;

/**
 * @description: 有返回值的异常抛出
 * @author: hzg
 * @create: 2019-09-09 10:59
 **/

public class Exception_method_has_return {
    public static  String testString(String s){
        try{
            return "aaa";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "bb";
    }


    public static void main(String[] args) {
        System.out.println(testString("bbb"));
    }
}

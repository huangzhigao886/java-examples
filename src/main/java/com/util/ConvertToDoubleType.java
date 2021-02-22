package com.util;

/**
 * @description: 将数据类型转换成Double类型
 * @author: hzg
 * @create: 2019-09-06 11:26
 **/

public class ConvertToDoubleType {
    public static Double pareseField(Object src) {
        if (src instanceof Integer) {
            return ((Double) src).doubleValue();
        } else {
            return (Double) src;
        }
    }

    public static void main(String[] args) {
//        Object src = new Object();
//        System.out.println(src instanceof Integer);
        Object src = new Double("16.123L");
        System.out.println(pareseField(src));

    }
}

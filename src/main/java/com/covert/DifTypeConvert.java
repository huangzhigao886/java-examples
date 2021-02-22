package com.covert;

import cn.hutool.core.convert.Convert;

import java.util.List;

public class DifTypeConvert {
    public static void main(String[] args) {
        String str = "123";
        Double value = Convert.toDouble(str);
        System.out.println(value);
        String[] strArr = {"11","12","13"};
        List<String> stringList = (List<String>) Convert.toList(strArr);
        System.out.println(stringList);
    }
}

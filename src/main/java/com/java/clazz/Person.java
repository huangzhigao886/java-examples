package com.java.clazz;

import cn.hutool.core.util.ObjectUtil;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String name;
    private String sex;

    public Person(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Person() {
    }

    public static void main(String[] args) throws IllegalAccessException {
        String ss = "count000dfdfsdff1count123";
        String regex = "count\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()){
            System.out.println(matcher.group(0));
        }
    }
}

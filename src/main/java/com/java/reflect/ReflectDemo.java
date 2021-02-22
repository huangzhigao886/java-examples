package com.java.reflect;

import org.reflections.Reflections;

import java.util.Set;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/11
 * @Description:
 */
public class ReflectDemo {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("my.package");
        System.out.println(reflections);
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(AnnotationDemo.class);
        System.out.println("aaa");
    }
}

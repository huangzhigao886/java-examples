package com.java.clazz;

/**
 * @Auther: huangzhigao
 * @Date: 2020/12/14
 * @Description:
 */
public class ClassReflect {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.java.clazz.Person");
        Object o = aClass.newInstance();
    }
}

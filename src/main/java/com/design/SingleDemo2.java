package com.design;

/**
 * @Auther: huangzhigao
 * @Date: 2021/1/13
 * @Description:
 */
public class SingleDemo2 {
    public static class SingletonHolder {
        private static final SingleDemo2 INSTANCE = new SingleDemo2();
    }

    public static SingleDemo2 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

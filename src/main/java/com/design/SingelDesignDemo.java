package com.design;

import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;

/**
 * @Auther: huangzhigao
 * @Date: 2021/1/13
 * @Description:
 */
public class SingelDesignDemo {
    private static SingelDesignDemo instance;

    private SingelDesignDemo() {
    }

    public static synchronized SingelDesignDemo getInstance() {
        if (instance == null) {
            synchronized (SingelDesignDemo.class) {
                if (instance == null) {
                    instance = new SingelDesignDemo(); //非原子操作
                }
            }
        }

        return instance;
    }
}

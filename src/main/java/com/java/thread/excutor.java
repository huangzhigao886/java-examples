package com.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/23
 * @Description:
 */
public class excutor {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 8; i++) {
            executor.execute(() -> {
                System.out.println("线程:" + Thread.currentThread().getName());
            });
            if (i==8){
                executor.shutdown();
            }
        }
    }
}

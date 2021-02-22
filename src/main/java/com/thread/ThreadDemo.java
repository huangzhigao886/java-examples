package com.thread;

import cn.hutool.core.thread.ExecutorBuilder;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/19
 * @Description:
 */
public class ThreadDemo {
    public static void main(String[] args) {

//        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque();
//        for (int i = 0; i < 100; i++) {
//            list.add("uuid-" + i);
//        }
//        int count = 0;
//
//        ExecutorService executor = ExecutorBuilder.create()
//                .setCorePoolSize(5)
//                .setMaxPoolSize(10)
//                .setWorkQueue(new LinkedBlockingQueue<>(100))
//                .build();
//
//
//        for (int i = 0; i < 10; i++) {
//            executor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(list.poll());
//                    System.out.println(Thread.currentThread().getName());
//                }
//            });
//        }
//        executor.shutdown();
    }


    @Test
    public void testMulThread() throws InterruptedException {

        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque();
        ExecutorService executor = ExecutorBuilder.create()
                .setCorePoolSize(5)
                .setMaxPoolSize(10)
                .setWorkQueue(new LinkedBlockingQueue<>(20))
                .build();
        long currentTimeMillis = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 100; i++) {
            list.add("uuid-" + i);
            count++;
            while (true) {
                try {
                    executor.submit(new RunnableTest(0, list));
                    break;
                } catch (Exception e) {
                    Thread.sleep(1000);
                }
            }


        }
        System.out.println(count);
        while (true) {
            if (list.size() == 0) {
                System.out.println("任务运行完毕");
                executor.shutdown();
                break;
            }
        }

        System.out.println("提交任务总计耗时1:" + (System.currentTimeMillis() - currentTimeMillis));

    }
}

package com.thread;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Auther: huangzhigao
 * @Date: 2020/12/28
 * @Description:
 */
public class RunnableTest implements Runnable {
    private int count;


    private ConcurrentLinkedDeque<String> values;

    public RunnableTest(int count, ConcurrentLinkedDeque<String> values) {
        this.count = count;
        this.values = values;
    }

    @Override
    public void run() {
        String poll = values.poll();
        while (true) {
            count++;
            try {
                if (count < 2) {
                    System.out.println(poll);
                }
                int v = 1 / 0;
            } catch (Exception e) {
                if (count == 2) {
                    break;
                }
            }
        }

    }
}

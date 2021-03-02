package com.mq;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: huangzhigao
 * @Date: 2021/2/28
 * @Description:
 */
public class QueueDemo {
    public static void main(String[] args) {
        Queue<Object> objects = new LinkedList<>();
        producer(objects);
        consumer(objects);
    }

    public static void producer(Queue<Object> objects) {
        objects.add(1);
        objects.add(2);
        objects.add(3);
    }

    public static void consumer(Queue<Object> queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}

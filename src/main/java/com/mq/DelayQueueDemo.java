package com.mq;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: huangzhigao
 * @Date: 2021/2/28
 * @Description:
 */
public class DelayQueueDemo {
    public static DelayQueue<Delayed> delayedQueue = new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {
        producer();
        consumer();
    }

    public static void producer() {
        delayedQueue.add(new MyDelay("战三", 2000));
        delayedQueue.add(new MyDelay("战四", 5000));
        delayedQueue.add(new MyDelay("战五", 10000));
    }

    public static void consumer() throws InterruptedException {
        System.out.println("当前的执行时间：" + DateFormat.getDateTimeInstance().format(new Date()));
        while (!delayedQueue.isEmpty()) {
            System.out.println(delayedQueue.take());
        }
        System.out.println("消费后执行时间：" + DateFormat.getDateTimeInstance().format(new Date()));
    }


    static class MyDelay implements Delayed {
        private String delayName;
        long delayTime = System.currentTimeMillis();

        public MyDelay(String delayName, long delayTime) {
            this.delayName = delayName;
            this.delayTime = this.delayTime + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else {
                return 0;
            }
        }


        @Override
        public String toString() {
            return delayName;
        }
    }
}

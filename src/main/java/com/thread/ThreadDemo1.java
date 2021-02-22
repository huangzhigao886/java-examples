package com.thread;

/**
 * @Auther: huangzhigao
 * @Date: 2020/8/19
 * @Description:
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
    }
}

class Thread1 extends Thread{
    @Override
    public void run() {
        System.out.println("创建小鹌鹑");
    }
}

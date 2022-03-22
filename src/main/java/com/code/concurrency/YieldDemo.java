package com.code.concurrency;

/**
 * Created by yankefei on 2022/3/16.
 */
public class YieldDemo {

    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    if (i==50){
                        Thread.yield();
                    }
                }
            }
        };
        Thread t1 = new Thread(task, "A");
        Thread t2 = new Thread(task, "B");
        t1.start();
        t2.start();
    }
}

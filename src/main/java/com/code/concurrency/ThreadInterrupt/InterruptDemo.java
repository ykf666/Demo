package com.code.concurrency.ThreadInterrupt;

import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2021/1/4.
 */
public class InterruptDemo {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            try {
                //wait,sleep,join方法会响应线程中断，抛出中断异常且中断标志被清除
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("线程中断标志：" + currentThread.isInterrupted());
                //该方法设置线程中断标志为true
                currentThread.interrupt();
                System.out.println("线程中断标志：" + currentThread.isInterrupted());
                //静态方法，清除线程中断标志
                Thread.interrupted();
                System.out.println("线程中断标志：" + currentThread.isInterrupted());
            }
        });
        t.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

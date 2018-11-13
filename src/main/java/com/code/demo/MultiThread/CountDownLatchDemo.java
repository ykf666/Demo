package com.code.demo.MultiThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yankefei on 2018/11/13.
 * <p>
 * countDownLatch不可能重新初始化或者修改CountDownLatch对象内部计数器的值，
 * 一个线程调用countdown方法happen-before，另外一个线程调用await方法
 * <p>
 * 线程执行完成后，计数器减一操作，当计数器为0，表示线程执行完成
 */
public class CountDownLatchDemo {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch downLatch = new CountDownLatch(2);

        new Thread("thread1") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    count.getAndIncrement();
                }
                downLatch.countDown();
            }
        }.start();

        new Thread("thread2") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    count.getAndIncrement();
                }
                downLatch.countDown();
            }
        }.start();

        System.out.println(count);
        //计数器为0之前，主线程一直处于等待状态
        downLatch.await();
        System.out.println(count);
    }
}

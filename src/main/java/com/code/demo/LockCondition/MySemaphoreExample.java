package com.code.demo.LockCondition;

import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2020/3/8.
 */
public class MySemaphoreExample {

    public static void main(String[] args) {
        MySemaphore semaphore = new MySemaphore(5);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "运行10秒");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    semaphore.release();
                }
            }, "Thread-" + i).start();
        }
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

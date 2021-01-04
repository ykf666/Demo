package com.code.concurrency.MultiThread;

import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2018/11/26.
 */
public class ThreadInterruptDemo implements Runnable {

    //是否阻塞
    private volatile static boolean isBlock = false;

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new ThreadInterruptDemo(), "ThreadInterruptDemo");
        testThread.start();
        ThreadInterruptDemo.isBlock = true;
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testThread.interrupt();
        }).start();
        int count = 0;
        while (testThread.isInterrupted()) {
            count++;
            System.out.println("testThread have interrupted!");
        }
        testThread.join();
        System.out.println("count=" + count + ", main end");
    }

    @Override
    public void run() {
        while (!isBlock) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("caught exception right now: " + e);
            }
        }
    }
}

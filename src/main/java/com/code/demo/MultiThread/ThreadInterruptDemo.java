package com.code.demo.MultiThread;

/**
 * Created by yankefei on 2018/11/26.
 */
public class ThreadInterruptDemo implements Runnable {

    //是否阻塞
    private volatile static boolean isBlock = false;

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new ThreadInterruptDemo(), "ThreadInterruptDemo");
        testThread.start();
        Thread.sleep(1000);
        ThreadInterruptDemo.isBlock = true;
        testThread.interrupt();
        while (testThread.isInterrupted()){
            System.out.println("testThread have interrupted!");
        }
        System.out.println("main end");
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

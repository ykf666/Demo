package com.code.concurrency.MultiThread;

/**
 * Created by yankefei on 2018/11/26.
 */
public class DeadLockDemo {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread("thread-0") {
            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " get lock1");
                    try {
                        Thread.sleep(1000);
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName() + " get lock2");
                    }
                }
                System.out.println(Thread.currentThread().getName() + " lock end");
            }
        }.start();

        new Thread("thread-1") {
            @Override
            public void run() {
                Thread.currentThread().interrupt();
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " get lock2");
                    try {
                        Thread.sleep(1000);
                        Thread.interrupted();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName() + " get lock1");
                        lock1.notify();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " lock end");
            }
        }.start();
    }
}

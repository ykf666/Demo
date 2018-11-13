package com.code.demo.MultiThread;

/**
 * Created by yankefei on 2018/11/12.
 */
public class AtomicIntegerLockDemo {

    private final static AtomicIntegerLock lock = new AtomicIntegerLock();
    private int count = 0;

    private void add() {
        lock.lock();
        this.count += 1;
        lock.unlock();
    }

    public void twoThreadAdd() throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                System.out.println("thread1 running...");
                for (int i = 0; i < 5000; i++) {
                    add();
                }
            }
        };

        Thread thread2 = new Thread("thread2") {
            @Override
            public void run() {
                System.out.println("thread2 running...");
                for (int i = 0; i < 5000; i++) {
                    add();
                }
            }
        };

        thread1.start();
        thread2.start();

        //顺序执行两个线程
        thread1.join();
        thread2.join();

        System.out.println(count);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerLockDemo demo = new AtomicIntegerLockDemo();
        demo.twoThreadAdd();
    }

}

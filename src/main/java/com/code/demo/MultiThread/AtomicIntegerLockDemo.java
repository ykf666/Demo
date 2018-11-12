package com.code.demo.MultiThread;

/**
 * Created by yankefei on 2018/11/12.
 */
public class AtomicIntegerLockDemo {

    private static AtomicIntegerLock lock = new AtomicIntegerLock();
    private int count = 0;

    private void add() {
        this.count += 1;
    }

    public void twoThreadAdd() throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                lock.lock();
                for (int i = 0; i < 5000; i++) {
                    add();
                }
                lock.unlock();
            }
        };

        Thread thread2 = new Thread("thread2") {
            @Override
            public void run() {
                lock.lock();
                for (int i = 0; i < 5000; i++) {
                    add();
                }
                lock.unlock();
            }
        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(count);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerLockDemo demo = new AtomicIntegerLockDemo();
        demo.twoThreadAdd();
    }

}

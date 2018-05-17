package com.code.demo.MultiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yan.kefei
 * @date 2018/5/17 16:29
 */
public class LockDemo {

    // true为公平锁，默认不填为非公平锁，公平锁保证锁的顺序获取
    private static Lock lock = new ReentrantLock(true);

    private void insert(Thread thread) {
        while (true) {
            if (lock.tryLock()) {
                try {
                    System.out.println(thread.getName() + " 得到了锁: "+ lock.hashCode());
//                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(thread.getName() + " 释放了锁: "+ lock.hashCode());
                    lock.unlock();
                }
                break;
            } else {
                System.out.println(thread.getName() + " 获取锁失败: "+ lock.hashCode());
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                continue;
            }
        }
    }

    public static void main(String[] args) {
        final LockDemo demo = new LockDemo();
        final LockDemo demo2 = new LockDemo();
        new Thread("thread-0") {
            @Override
            public void run() {
                demo.insert(Thread.currentThread());
            }
        }.start();

        new Thread("thread-2") {
            @Override
            public void run() {
                demo2.insert(Thread.currentThread());
            }
        }.start();

        new Thread("thread-1") {
            @Override
            public void run() {
                demo.insert(Thread.currentThread());
            }
        }.start();

    }
}

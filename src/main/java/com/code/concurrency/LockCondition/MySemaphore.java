package com.code.concurrency.LockCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yankefei on 2020/3/8.
 */
public class MySemaphore {

    private int permits;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition permitsAvailable = lock.newCondition();

    public MySemaphore(int permits) {
        lock.lock();
        try {
            this.permits = permits;
        } finally {
            lock.unlock();
        }
    }

    public void acquire() {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.await();
            }
            --permits;
            System.out.println(Thread.currentThread().getName() + "获得许可");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            ++permits;
            System.out.println(Thread.currentThread().getName() + "释放许可");
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

}

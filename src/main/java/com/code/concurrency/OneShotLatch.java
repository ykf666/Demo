package com.code.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * AQS实现二元闭锁
 * Created by yankefei on 2020/4/16.
 */
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) {
        OneShotLatch oneShotLatch = new OneShotLatch();
        Semaphore semaphore = new Semaphore(5);
        ReentrantLock reentrantLock = new ReentrantLock();
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        Map map = new HashMap();
        int h;
        String a = "2";
        char[] chars = a.toCharArray();
        System.out.println((int) chars[0]);
        System.out.println(0 + chars[0]);
        System.out.println(a.hashCode());
        int h2 = (h = a.hashCode()) ^ (h >>> 16);
        System.out.println(h2);
        map.put(a, "2");
        map.put(a, "3");

        for (int i = 1; i <= 10; i++) {
            new UserThread(oneShotLatch).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("unlock...");
            oneShotLatch.signal();
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class UserThread extends Thread {

        private OneShotLatch oneShotLatch;

        UserThread(OneShotLatch oneShotLatch) {
            this.oneShotLatch = oneShotLatch;
        }

        @Override
        public void run() {
            try {
                oneShotLatch.await();
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

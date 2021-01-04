package com.code.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

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

    private class Sync extends AbstractQueuedLongSynchronizer {

        //独占模式获取锁NoFair
        @Override
        protected boolean tryAcquire(long arg) {
            final Thread current = Thread.currentThread();
            long c = getState();
            if (c == 0) {
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (current == getExclusiveOwnerThread()) {
                long nextc = c + arg;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }

        //独占模式释放锁Nofair
        @Override
        protected boolean tryRelease(long arg) {
            long c = getState() - arg;
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

        //共享模式获取锁
        @Override
        protected long tryAcquireShared(long arg) {
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(long arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) {
        OneShotLatch oneShotLatch = new OneShotLatch();
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
                if (this.isInterrupted()) {
                    System.out.println("线程" + this.getName() + "已中断");
                }
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

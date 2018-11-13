package com.code.demo.MultiThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yankefei on 2018/11/12.
 * <p>
 * 利用AtomicInteger实现自旋锁，是一种乐观锁
 * 如果lock为1，则表示锁被占用
 */
public class AtomicIntegerLock {

    private AtomicInteger lockState = new AtomicInteger(0);

    public void lock() {
        for (; ; ) {
            if (lockState.get() == 1) {
                continue;
            } else if (lockState.compareAndSet(0, 1)) {
                break;
            }
        }
    }

    public void unlock() {
        lockState.set(0);
    }

}
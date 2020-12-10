package com.code.demo.LockCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yankefei on 2020/3/7.
 */
public class ConditionBoundedArrayQueue<T> {

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final T[] items = (T[]) new Object[10];

    private int head, tail, count;

    public void put(T item) throws InterruptedException {
        lock.lock();
        String threadName = Thread.currentThread().getName();
        try {
            while (count == items.length) {
                System.out.println("queue has full, " + threadName + " wait");
                notFull.await();
            }
            items[tail] = item;
            if (++tail == items.length)
                tail = 0;
            ++count;
            System.out.println(threadName + " put - " + item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        String threadName = Thread.currentThread().getName();
        try {
            while (count == 0) {
                System.out.println("queue is null, " + threadName + " wait");
                notEmpty.await();
            }
            T t = items[head];
            if (++head == items.length)
                head = 0;
            --count;
            System.out.println(Thread.currentThread().getName() + " take - " + t);
            notFull.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }
}

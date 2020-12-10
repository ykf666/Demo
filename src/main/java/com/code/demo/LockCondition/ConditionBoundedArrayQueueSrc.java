package com.code.demo.LockCondition;

/**
 * Created by yankefei on 2020/12/9.
 */
public class ConditionBoundedArrayQueueSrc<T> {

    private final T[] items = (T[]) new Object[10];

    private int head, tail, count;

    public void put(T item) throws InterruptedException {
        synchronized (this) {
            String threadName = Thread.currentThread().getName();
            while (count == items.length) {
                System.out.println("queue has full，" + threadName + " wait");
                this.wait();
            }
            items[tail] = item;
            if (++tail == items.length)
                tail = 0;
            ++count;
            System.out.println(Thread.currentThread().getName() + " put - " + item);
            this.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        synchronized (this) {
            String threadName = Thread.currentThread().getName();
            while (count == 0) {
                System.out.println("queue is null, " + threadName + " wait");
                this.wait();
            }
            T t = items[head];
            if (++head == items.length)
                head = 0;
            --count;
            System.out.println(Thread.currentThread().getName() + " take - " + t);
            this.notifyAll();
            return t;
        }
    }
}

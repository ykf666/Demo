package com.code.demo.WaitNotifyDemo;

import java.util.List;

/**
 * Created by yankefei on 2018/11/14.
 */
public class Producer implements Runnable {

    private final List<Integer> taskQueue;
    private final int MAX_CAPACITY;

    public Producer(List<Integer> QUEUE, int SIZE) {
        this.taskQueue = QUEUE;
        this.MAX_CAPACITY = SIZE;
    }

    @Override
    public void run() {
        int count = 0;
        for (; ; ) {
            try {
                produce(count++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 同步taskQueue对象，生产者和消费者线程竞争此对象的使用
    // 如果生产时发现队列容量已满，则调用wait方法，使当前线程进入等待状态
    public void produce(int i) throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.size() == MAX_CAPACITY) {
                System.out.println("Queue is full " + Thread.currentThread().getName()
                        + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }
            Thread.sleep(1000);
            taskQueue.add(i);
            System.out.println("Produced: " + i);
            taskQueue.notifyAll();
        }
    }

}

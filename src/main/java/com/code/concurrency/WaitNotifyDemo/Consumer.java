package com.code.concurrency.WaitNotifyDemo;

import java.util.List;

/**
 * Created by yankefei on 2018/11/14.
 */
public class Consumer implements Runnable {

    private final List<Integer> taskQueue;

    public Consumer(List<Integer> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                System.out.println("Queue is empty " + Thread.currentThread().getName()
                        + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }
            Thread.sleep(1000);
            int i = taskQueue.remove(0);
            System.out.println("Consumed: " + i);
            taskQueue.notifyAll();
        }
    }

}

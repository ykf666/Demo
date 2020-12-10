package com.code.demo.LockCondition;

import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2020/3/7.
 */
public class ConditionExample {

    final static ConditionBoundedArrayQueueSrc buffer = new ConditionBoundedArrayQueueSrc();
//  final static ConditionBoundedArrayQueue buffer = new ConditionBoundedArrayQueue<Integer>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 3; i++) {
            new Producer("producer-" + i).start();
        }
        for (int i = 1; i <= 2; i++) {
            new Consumer("consumer-" + i).start();
        }
        //主线程等待，一直运行下去
        Thread.currentThread().join();
    }

    private static class Producer extends Thread {

        Producer(String threadName) {
            this.setName(threadName);
        }

        public void run() {
            Integer i = 1;
            while (true) {
                try {
                    buffer.put(i);
                    i++;
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer extends Thread {

        Consumer(String threadName) {
            this.setName(threadName);
        }

        public void run() {
            while (true) {
                try {
                    buffer.take();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

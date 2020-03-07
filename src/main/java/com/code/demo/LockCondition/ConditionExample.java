package com.code.demo.LockCondition;

import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2020/3/7.
 */
public class ConditionExample {

    public static void main(String[] args) throws InterruptedException {
        ConditionBoundedArrayQueue buffer = new ConditionBoundedArrayQueue<Integer>();
        new Thread(() -> {
            Integer i = 1;
            while (true) {
                try {
                    buffer.put(i);
                    ++i;
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    buffer.take();
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (true) {
            TimeUnit.SECONDS.sleep(5);
        }
    }
}

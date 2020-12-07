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
                    if (i == 5) {
                        System.out.println("put子线程退出");
                        break;
                    }
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    Integer item = (Integer) buffer.take();
                    if (item == 4) {
                        System.out.println("take子线程退出");
                        break;
                    }
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //等待子线程结束
        Thread.currentThread().join();
    }
}

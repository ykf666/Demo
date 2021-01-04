package com.code.concurrency.MultiThread;

/**
 * @author yan.kefei
 * @date 2018/5/17 09:11
 */
public class VolatileDemo {

    private static volatile int count = 0;

    private void add() {
        synchronized (this) {
            count++;
        }
    }

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        demo.add();
                    }
                }
            }.start();
        }

        // 保证线程执行结束后，idea是大于2，eclipse是大于1
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(count);
    }

}

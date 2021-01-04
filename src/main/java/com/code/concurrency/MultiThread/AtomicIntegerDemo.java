package com.code.concurrency.MultiThread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    private static AtomicInteger count = new AtomicInteger(0);

    private static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                count.getAndIncrement();
            }
        }
    }

    public static void main(String[] args) {
        for (int k = 0; k < 10; k++) {
            new Thread(new AddThread()).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(count);
    }
}

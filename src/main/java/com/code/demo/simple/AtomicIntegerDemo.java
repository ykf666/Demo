package com.code.demo.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
        ExecutorService threadpool = Executors.newFixedThreadPool(10);

        for (int k = 0; k < 10; k++) {
            threadpool.submit(new AddThread());
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result of acumulated sum =" + count);
        threadpool.shutdown();
    }
}

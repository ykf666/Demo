package com.code.designPatterns.Sigleton;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by yankefei on 2020/2/14.
 */
public class SingletonMain {

    private final static Integer THREAD_COUNT = 5000;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
//            new ClassA(String.valueOf(i),barrier).start();
            new ClassB(String.valueOf(i), barrier).start();
        }
    }

    private static class ClassA extends Thread {
        CyclicBarrier barrier;

        public ClassA(String name, CyclicBarrier barrier) {
            super(name);
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread-A-" + this.getName() + " ready");
                barrier.await();
                SingletonClass obj = SingletonClass.INSTANCE;
                System.out.println("Thread-A-" + this.getName() + " " + obj.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ClassB extends Thread {
        CyclicBarrier barrier;

        public ClassB(String name, CyclicBarrier barrier) {
            super(name);
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread-A-" + this.getName() + " ready");
                barrier.await();
                SingletonClass obj = SingletonClass.getInstance();
                System.out.println("Thread-B-" + this.getName() + " " + obj.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

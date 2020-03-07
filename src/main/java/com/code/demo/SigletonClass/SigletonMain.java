package com.code.demo.SigletonClass;

import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2020/2/14.
 */
public class SigletonMain {

    private static class ClassA extends Thread {

        public ClassA(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                System.out.println("Thread-" + this.getName() + " " + SigletonClass.getInstance());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ClassA ca = new ClassA(String.valueOf(i));
            ca.start();
        }
    }
}

package com.code.demo.Simple;

import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2020/2/14.
 */
public class SigletonMain {

    private static class ClassA extends Thread {
        @Override
        public void run() {
            while (true){
                System.out.println(SigletonClass.getInstance());
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
            ClassA ca = new ClassA();
            ca.start();
        }
    }
}

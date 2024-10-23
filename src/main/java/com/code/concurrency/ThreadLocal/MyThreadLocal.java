package com.code.concurrency.ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2020/4/19.
 */
public class MyThreadLocal {

    private static final ThreadLocal<String> THREAD_LOCAL =
            ThreadLocal.withInitial(() -> "default");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                THREAD_LOCAL.set(Thread.currentThread().getName());
                System.out.println(THREAD_LOCAL.get());
            }, "Thread-" + i).start();
        }
        TimeUnit.SECONDS.sleep(5);
    }
}

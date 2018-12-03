package com.code.demo.MultiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2018/12/3.
 *
 * 1.8加入的异步应用类
 */
public class CompletableDemo {

    public static void main(String[] args) {
        CompletionService completionService = new CompletionService() {
            @Override
            public Future submit(Callable task) {
                return null;
            }

            @Override
            public Future submit(Runnable task, Object result) {
                return null;
            }

            @Override
            public Future take() throws InterruptedException {
                return null;
            }

            @Override
            public Future poll() {
                return null;
            }

            @Override
            public Future poll(long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }
        };
    }

}

package com.code.demo.MultiThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by yankefei on 2018/12/3.
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        Thread thread = new Thread(futureTask);
        thread.start();

        int result = 0;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("futureTask计算结果为：" + result);
    }
}

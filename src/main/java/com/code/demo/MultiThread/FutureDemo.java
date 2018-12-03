package com.code.demo.MultiThread;

import java.util.concurrent.*;

/**
 * Created by yankefei on 2018/12/3.
 */
public class FutureDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        //Future和FutureTask的区别
        //Futrue是只能结合ExecutorService线程池使用，new Thread是不支持Future构造的
        Future<Integer> future = executorService.submit(task);
        executorService.shutdown();

        int result = 0;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("运行结果：" + result);
    }

}

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " doing something...");
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += i;
            Thread.sleep(1000);
        }
        return sum;
    }
}
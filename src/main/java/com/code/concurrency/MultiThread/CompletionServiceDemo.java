package com.code.concurrency.MultiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yankefei on 2018/12/3.
 * <p>
 * 通过阻塞队列+FutureTask实现，任务先完成的，先获取到
 */
public class CompletionServiceDemo {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        List<Integer> results = new ArrayList<>();

        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executors);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            futures.add(completionService.submit(new Task(5)));
        }

        //使用内部阻塞队列获取
        try {
            for (int i = 0; i < 5; i++) {
                Integer result = completionService.take().get();
                results.add(result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executors.shutdown();
        }

    }

}

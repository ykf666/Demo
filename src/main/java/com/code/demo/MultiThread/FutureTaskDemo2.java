package com.code.demo.MultiThread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by yankefei on 2018/12/3.
 * <p>
 * 多线程并行计算时，计算结果顺序未知
 */
public class FutureTaskDemo2 {

    public static void main(String[] args) {
        //开启多线程
        ExecutorService executors = Executors.newFixedThreadPool(5);
        List<FutureTask<Integer>> futureTasks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task(i + 1));
            futureTasks.add(futureTask);
            executors.submit(futureTask);
        }

        List<Integer> results = new ArrayList<>();
        Iterator iterator = futureTasks.iterator();
        while (iterator.hasNext()){
            FutureTask<Integer> futureTask = (FutureTask<Integer>) iterator.next();
            try {
                if (futureTask.isDone() && !futureTask.isCancelled()){
                    int result = futureTask.get();
                    iterator.remove();
                    results.add(result);
                } else {
                    Thread.sleep(500);
                    System.out.println("未得到结果，等待...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("计算所得结果为：" + results);
    }
}

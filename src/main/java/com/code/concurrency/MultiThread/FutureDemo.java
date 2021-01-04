package com.code.concurrency.MultiThread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yankefei on 2018/12/3.
 */
public class FutureDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //Future和FutureTask的区别
        //Futrue是只能结合ExecutorService线程池使用，new Thread是不支持Future构造的
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i=1;i<=5;i++){
            Task task = new Task(5);
            futures.add(executorService.submit(task));
        }

        //如果有多个任务提交执行，返回结果也将按照提交顺序获得
        List<Integer> results = new ArrayList<>();
        try {
            Iterator iterator = futures.iterator();
            //方式一：阻塞主线程获取结果
/*            while (iterator.hasNext()){
                Future<Integer> future = (Future<Integer>) iterator.next();
                int result = future.get();
                results.add(result);
            }*/

            //方式二：轮询结果
            for (Future<Integer> future : futures) {
                while (true) {
                    if (future.isDone() && !future.isCancelled()) {
                        int result = future.get();
                        results.add(result);
                        break;
                    } else {
                        Thread.sleep(500);
                        System.out.println("未得到结果，等待...");
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
        System.out.println("运行结果：" + results);
    }

}

class Task implements Callable<Integer> {

    private final int num;

    public Task(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " doing something...");
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += i;
            Thread.sleep(1000);
        }
        System.out.println(Thread.currentThread().getName() + " 计算结果为：" + sum);
        return sum;
    }
}
package com.code.demo.MultiThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by yankefei on 2018/12/4.
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        //结果集
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        List<Integer> taskList = Arrays.asList(new Integer[]{2, 3, 4, 5, 6});

        try {
            for (Integer i : taskList) {
                final int j = i;
                CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> taskList.get(j),
                        executorService)
                        //thenAccept 只接受不返回，不影响结果
                        .thenApply(e -> String.valueOf(e))
                        //获取任务完成先后顺序
                        .whenComplete((v, e) -> {
                    System.out.println("任务" + v + "完成!result=" + v + "，异常 e=" + e + "," + new Date());
                    list2.add(v);
                });
                futureList.add(future);
            }
            //流式获取结果，此处是根据任务添加顺序获取的结果
            futureList.stream().filter(f-> f!=null).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}

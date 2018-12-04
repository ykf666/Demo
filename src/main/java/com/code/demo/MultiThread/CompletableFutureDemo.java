package com.code.demo.MultiThread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yankefei on 2018/12/4.
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Integer> taskList = Arrays.asList(new Integer[]{2, 3, 4, 5, 6});

        try {
            for (Integer i : taskList){
                CompletableFuture<String> future = CompletableFuture.supplyAsync(()-> new String(), executorService);
            }
        }catch (Exception e){

        }
    }
}

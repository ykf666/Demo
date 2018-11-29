package com.code.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ParallelStreamDemo {

    public static void main(String[] args) {
        //调用parallel产生一个并行流
        /*IntStream.range(1, 100).parallel().peek(ParallelStreamDemo::debug).count();*/

        //先并行，再串行
        //多次调用parallel/sequential函数，以最后一次函数为准
/*        IntStream.range(1, 100)
                .parallel().peek(ParallelStreamDemo::debug)
                .sequential().peek(ParallelStreamDemo::debug2)
                .count();*/

        //并行使用的线程池：ForkJoinPool.commonPool
        //默认使用的线程数，为当前cpu核心数
        //使用以下属性可修改线程池的线程数
/*        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
        IntStream.range(1, 100).parallel().peek(ParallelStreamDemo::debug).count();*/

        //使用自己的线程池，不使用默认，防止阻塞
        //线程池名称为：ForkJoinPool-1
        ForkJoinPool pool = new ForkJoinPool(20);
        pool.submit(() -> IntStream.range(1, 100).parallel().peek(ParallelStreamDemo::debug).count());
        pool.shutdown();

        synchronized (pool) {
            System.out.println(Thread.holdsLock(pool));
            try {
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.holdsLock(pool));
    }

    public static void debug(int i) {
        System.out.println(Thread.currentThread().getName() + " debug-" + i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debug2(int i) {
        System.err.println("debug2-" + i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
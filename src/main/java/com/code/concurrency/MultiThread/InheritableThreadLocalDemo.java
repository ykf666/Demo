package com.code.concurrency.MultiThread;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;

import java.util.concurrent.*;

/**
 *
 * InheritableThreadLocal仅用于新建线程的内容传递，如果线程存在复用情况，则无法避免内容错误问题
 * Created by yankefei on 2023/2/22.
 */
public class InheritableThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocal tl = new ThreadLocal();
        tl.set("ThreadLocal");
        InheritableThreadLocal<String> itl = new InheritableThreadLocal<>();
        itl.set("InheritableThreadLocal");

        System.out.println("主线程获取值：" + tl.get());
        System.out.println("主线程获取值：" + itl.get());
        Thread thread = new Thread(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
        });
        thread.start();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
        });
        tl.set("ThreadLocal-2");
        itl.set("InheritableThreadLocal-2");

        executorService.execute(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
        });


        //自定义线程池
        ThreadFactory threadFactory = new NamedThreadFactory("my");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        tl.set("ThreadLocal-3");
        itl.set("InheritableThreadLocal-3");
        threadPoolExecutor.execute(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
        });
        tl.set("ThreadLocal-4");
        itl.set("InheritableThreadLocal-4");
        threadPoolExecutor.execute(() -> {
            Thread t = Thread.currentThread();
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
        });
    }

}

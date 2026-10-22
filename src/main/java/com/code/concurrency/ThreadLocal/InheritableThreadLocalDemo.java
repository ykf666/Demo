package com.code.concurrency.ThreadLocal;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;

import java.util.concurrent.*;

/**
 *
 * InheritableThreadLocal仅用于新建线程的上下文传递，如果使用线程池，线程存在复用情况，则无法避免获取内容错误
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
        System.out.println("--------------------------------------------------");

        tl.set("ThreadLocal-1");
        itl.set("InheritableThreadLocal-1");
        Thread thread = new Thread(() -> {
            System.out.println("自定义线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("自定义线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
            System.out.println("--------------------------------------------------");
        });
        thread.start();

        tl.set("ThreadLocal-2");
        itl.set("InheritableThreadLocal-2");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            System.out.println("线程池线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("线程池线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
            System.out.println("--------------------------------------------------");
        });

        tl.set("ThreadLocal-3");
        itl.set("InheritableThreadLocal-3");
        executorService.execute(() -> {
            System.out.println("线程池线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("线程池线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
            System.out.println("--------------------------------------------------");
        });

        tl.set("ThreadLocal-4");
        itl.set("InheritableThreadLocal-4");
        //自定义线程池
        ThreadFactory threadFactory = new NamedThreadFactory("my");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(() -> {
            System.out.println("自定义线程池线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("自定义线程池线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
            System.out.println("--------------------------------------------------");
        });
        tl.set("ThreadLocal-5");
        itl.set("InheritableThreadLocal-5");
        threadPoolExecutor.execute(() -> {
            System.out.println("自定义线程池线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            System.out.println("自定义线程池线程" + Thread.currentThread().getName() + "获取值：" + itl.get());
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        threadPoolExecutor.shutdown();
    }

}

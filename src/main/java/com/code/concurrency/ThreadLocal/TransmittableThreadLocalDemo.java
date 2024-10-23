package com.code.concurrency.ThreadLocal;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.*;

/**
 * 阿里开源的TransmittableThreadLocal，可以指定TtlExecutors线程池使用，实现线程间上下文传递
 * Created by yankefei on 2023/2/22.
 */
public class TransmittableThreadLocalDemo {

    public static void main(String[] args) {
        TransmittableThreadLocal<String> tl = new TransmittableThreadLocal<>();
        tl.set(Thread.currentThread().getName());

        Thread thread = new Thread(() -> {
            System.out.println("自定义线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            tl.set(Thread.currentThread().getName());
            System.out.println("自定义线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程" + Thread.currentThread().getName() + "获取值：" + tl.get());

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), new NamedThreadFactory("tl")));
        executorService.execute(() -> {
            System.out.println("线程池线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
        });
        ttlExecutorService.execute(() -> {
            System.out.println("线程池线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
            tl.set(Thread.currentThread().getName());
        });

        tl.set("mainThread-2");
        Thread thread2 = new Thread(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
        });
        thread2.start();
        executorService.execute(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
        });
        ttlExecutorService.execute(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "获取值：" + tl.get());
        });

        //关闭线程池
        executorService.shutdown();
        while (true) {
            try {
                if (executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                    System.out.println("线程池关闭成功");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

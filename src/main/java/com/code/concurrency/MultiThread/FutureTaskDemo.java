package com.code.concurrency.MultiThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by yankefei on 2018/12/3.
 * <p>
 * futureTask弥补了future只能用线程池提交的不足，可以使用Thread提交
 * get阻塞获取结果前，可以穿插其他业务逻辑
 *
 * 使用状态机和Unsafe.cas实现
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        FutureTask<Integer> futureTask = new FutureTask<>(new Task(5));
        Thread thread = new Thread(futureTask);
        thread.start();

        //线程启动后，主线程可执行其他逻辑
        boolean cancle = false;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int result = 0;
        try {
            System.out.println("主线程穿插业务执行完成！");
            if (cancle) {
                System.out.println(thread.getName() + "取消了...");
                futureTask.cancel(true);
            } else {
                result = futureTask.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long t3 = System.currentTimeMillis();
        System.out.println("主线程共耗时：" + (t3 - t1));
        System.out.println("futureTask计算结果为：" + result);
    }
}

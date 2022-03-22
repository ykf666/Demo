package com.code.concurrency.ThreadInterrupt;

import java.time.LocalDateTime;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by yankefei on 2021/1/4.
 */
public class ThreadParkInterruptDemo {

    public static void main(String[] args) {
        Thread t = new Thread(new ParkThread());
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "开始唤醒阻塞线程");
        t.interrupt();
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "结束");
    }
}

class ParkThread implements Runnable {
    @Override
    public void run() {
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "开始第一次阻塞");
        //它不会抛出中断异常，而是从park方法直接返回，不影响线程的继续执行，并且不会清除中断标志
        //所以紧接着再次调用park方法会直接返回，不会阻塞线程运行
        LockSupport.park();
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "结束第一次阻塞");
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "开始第二次阻塞2秒");
        //由于未清除中断标志，所以park方法继续直接返回
        LockSupport.parkNanos(2000000000);
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "结束第二次阻塞，阻塞失败");
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "清除中断标志");
        Thread.interrupted();
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "开始第三次阻塞2秒");
        LockSupport.parkNanos(2000000000);
        System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName() + "结束第三次阻塞");
    }
}

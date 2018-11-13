package com.code.demo.MultiThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yankefei on 2018/11/13.
 *
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 * 因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 *
 * CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
 *      1、CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行，侧重于线程A等待其他线程执行；
 *      2、而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行，侧重于线程之间相互等待；
 *      另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
 */
public class CyclicBarrierDemo {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        new Thread("thread1") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    count.getAndIncrement();
                }
                try {
                    //线程到达barrier状态
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread("thread2") {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    count.getAndIncrement();
                }
                try {
                    //线程到达barrier状态
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        System.out.println(count);
        //所有线程到达barrier状态，表示所有线程执行完成
        cyclicBarrier.await();
        System.out.println(count);
    }
}

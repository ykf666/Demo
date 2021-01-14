package com.code.basic.Collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yankefei on 2021/1/5.
 * 队列常用方法：
 * add() <--> remove()
 * offer() <--> poll()
 * put() <--> take()
 * peek()
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        //数组实现的有界阻塞队列（线程安全）
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        //add(), 添加元素，如果队列已满，则抛出异常，基于offer方法实现
        queue.add(1);
        //offer(), 添加元素，如果队列已满，返回false
        queue.offer(2);
        //offer(e, timeout, timeunit), 基于ReentrantLock.Condition实现可中断的阻塞
        try {
            queue.offer(3, 3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //put(), 基于ReentrantLock.Condition实现可中断的阻塞
        try {
            queue.put(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.forEach((item) -> System.out.print(item + " "));
        System.out.println();
        //poll(), 取出元素，如果队列为空，返回null
        Integer e = queue.poll();
        System.out.println("poll()方法取出元素：" + e);
        //poll(timeout, timeunit), 取出元素，基于ReentrantLock.Condition实现可中断的阻塞
        try {
            Integer e2 = queue.poll(3, TimeUnit.SECONDS);
            System.out.println("poll(timeout, timeunit)方法取出元素：" + e2);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        //take(), 取出元素，基于ReentrantLock.Condition实现可中断的阻塞
        try {
            Integer e3 = queue.take();
            System.out.println("take()方法取出元素：" + e3);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        //peek(), 查看队列头部元素，如果队列为空，返回nulll
        Integer e4 = queue.peek();
        System.out.println("peek()方法返回队列头部元素：" + e4);
        //remove(), 删除队列头部元素，如果队列为空，抛出异常，基于poll()方法实现
        queue.remove();
        queue.forEach((item) -> System.out.print(item + " "));
    }

}

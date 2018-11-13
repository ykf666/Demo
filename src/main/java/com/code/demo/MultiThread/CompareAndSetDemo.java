package com.code.demo.MultiThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yankefei on 2018/11/12.
 */
public class CompareAndSetDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(55);
        int addValue = atomicInteger.addAndGet(23);
        System.out.println(addValue);
        //compareAndSet(expect, update)，如果expect与内存值相同，则更新为update的值，返回true
        boolean reslut = atomicInteger.compareAndSet(78, 43);
        System.out.println(reslut);
        System.out.println(atomicInteger.get());
    }

}

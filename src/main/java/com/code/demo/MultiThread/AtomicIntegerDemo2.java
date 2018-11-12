package com.code.demo.MultiThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yankefei on 2018/11/12.
 */
public class AtomicIntegerDemo2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(55);
        int addValue = atomicInteger.addAndGet(23);
        System.out.println(addValue);
        boolean reslut = atomicInteger.compareAndSet(78, 43);
        System.out.println(reslut);
        System.out.println(atomicInteger.get());
    }

}

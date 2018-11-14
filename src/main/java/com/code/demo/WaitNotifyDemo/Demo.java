package com.code.demo.WaitNotifyDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yankefei on 2018/11/14.
 */
public class Demo {

    public static void main(String[] args) {
        List<Integer> tasks = new ArrayList<>();
        int max_capacity = 5;
        Thread t1 = new Thread(new Producer(tasks, max_capacity), "thread-producer");
        Thread t2 = new Thread(new Consumer(tasks), "thread-consumer");

        t1.start();
        t2.start();
    }

}

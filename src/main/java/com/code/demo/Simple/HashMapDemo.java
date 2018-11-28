package com.code.demo.Simple;

import java.util.HashMap;

/**
 * Created by yankefei on 2018/11/27.
 */
public class HashMapDemo {

    private static HashMap<Integer, String> map = new HashMap<>(2, 0.75f);

    public static void main(String[] args) {
        map.put(5, "C");

        //hashMap并非线程安全，resize时可能会导致循环链表的出现jkd1.7
       new Thread("Thread1") {
            @Override
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            }
        }.start();

        new Thread("Thread2") {
            @Override
            public void run() {
                map.put(3, "A");
                System.out.println(map);
            }
        }.start();

        if (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(map);
    }
}

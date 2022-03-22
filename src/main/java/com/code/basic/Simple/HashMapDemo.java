package com.code.basic.Simple;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by yankefei on 2018/11/27.
 */
public class HashMapDemo {

    private static final int MAXIMUM_CAPACITY = 1<<30;

    private static HashMap<Integer, String> map = new HashMap<>(2, 0.75f);

    public static void main(String[] args) {
        int a = 1<<2;
        System.out.println("MAXIMUM_CAPACITY="+ MAXIMUM_CAPACITY);
        System.out.println(a);
        System.out.println(tableForSize(7));
        HashMap hashMap = new HashMap(7);
        hashMap.put("1", "1");
    }

    private static void testResize(){
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

    private static int tableForSize(int cap){
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

}

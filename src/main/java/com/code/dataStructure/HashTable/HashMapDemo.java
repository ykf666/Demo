package com.code.dataStructure.HashTable;

/**
 * Created by yankefei on 2020/12/10.
 */
public class HashMapDemo {

    private final static int MAXIMUM_CAPACITY = (1 << 30);

    public static void main(String[] args) {
        System.out.println("HashMap的最大容量：" + MAXIMUM_CAPACITY);
        int capacity_1000 = tableSizeFor(1000);
        int capacity_10000 = tableSizeFor(10000);
        System.out.println("初始化容量1000，下次扩容容量：" + capacity_1000 + "，触发扩容值：" + capacity_1000 * 0.75);
        System.out.println("初始化容量10000，下次扩容容量：" + capacity_10000 + "，触发扩容值：" + capacity_10000 * 0.75);
    }

    private static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}

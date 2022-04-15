package com.code.algorithm.leetCode;

import java.util.*;

/**
 * case 146
 * Least recently used（最近最少使用）
 * linkedHashMap实现
 * Created by yankefei on 2022/3/21.
 */
public class LRUCache {

    private LinkedHashMap<Integer, Integer> map;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity);
    }

    public Integer get(int key) {
        Integer value = null;
        if (this.map.containsKey(key)) {
            value = this.map.get(key);
            this.map.remove(key);
            this.map.put(key, value);
        }
        return value;
    }

    public void put(Integer key, Integer value) {
        if (this.map.containsKey(key)) {
            this.map.remove(key);
        } else if (this.map.size() == this.capacity) {
            //删除最早的缓存
            this.map.remove(this.map.entrySet().iterator().next().getKey());
        }
        this.map.put(key, value);
    }

    public void show() {
        Set<Map.Entry<Integer, Integer>> entrySet = this.map.entrySet();
        System.out.println(Arrays.toString(entrySet.toArray()));
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.show();

        lruCache.get(1);
        lruCache.show();

        lruCache.put(3, 3);
        lruCache.show();

        lruCache.get(4);
        lruCache.show();

        lruCache.put(5, 5);
        lruCache.show();
    }

}

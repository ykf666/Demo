package com.code.basic.algorithm.leetCode;

import java.util.*;

/**
 * Least recently used（最近最少使用）
 * Created by yankefei on 2022/3/21.
 */
public class Case146_LRUCache {

    private LinkedHashMap<Integer, Integer> linkedHashMap;

    private int capacity;

    public Case146_LRUCache(int capacity) {
        this.capacity = capacity;
        linkedHashMap = new LinkedHashMap(capacity);
    }

    public Integer get(int key) {
        Integer value = null;
        if (this.linkedHashMap.containsKey(key)) {
            value = this.linkedHashMap.get(key);
            this.linkedHashMap.remove(key);
            this.linkedHashMap.put(key, value);
        }
        return value;
    }

    public void put(Integer key, Integer value) {
        if (this.linkedHashMap.containsKey(key)) {
            this.linkedHashMap.remove(key);
        } else if (this.linkedHashMap.size() == this.capacity) {
            //删除最早的缓存
            this.linkedHashMap.remove(this.linkedHashMap.entrySet().iterator().next().getKey());
        }
        this.linkedHashMap.put(key, value);
    }

    public void show() {
        Set<Map.Entry<Integer, Integer>> entrySet = this.linkedHashMap.entrySet();
        System.out.println(Arrays.toString(entrySet.toArray()));
    }

    public static void main(String[] args) {
        Case146_LRUCache lruCache = new Case146_LRUCache(2);
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

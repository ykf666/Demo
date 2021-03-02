package com.code.basic.Collections;

import org.apache.commons.lang3.RandomUtils;

import java.util.TreeMap;

/**
 * Created by yankefei on 2021/3/2.
 */
public class TreeMapDemo {

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            int index = RandomUtils.nextInt(1, 100);
            treeMap.put(index, String.valueOf(i));
            System.out.println("添加元素, " + index + " = " + i);
        }
        treeMap.forEach((key, value) ->
                System.out.println(key + " = " + value)
        );
    }
}

package com.code.leet;

import java.util.*;

/**
 * Created by yankefei on 2018/12/12.
 * <p>
 * 数组中前k个高频元素
 */
public class TopKFrequent {

    final static Integer[] arr = {1, 1, 1, 3, 2, 5, 2, 2, 2, 5};

    public static void main(String[] args) {
        //方法一：排序实现
        mySort(arr, 2);
    }

    private static void mySort(Integer[] arr, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            if (count < k) {
                System.out.println(entry.getKey());
                count++;
            }
        }
    }

}

package com.code.leetCode;

import java.util.*;

/**
 * Created by yankefei on 2018/12/12.
 * <p>
 * 数组中前k个高频元素
 */
public class TopKFrequent {

    final static Integer[] arr = {1, 1, 1, 3, 2, 5, 2, 2, 2, 5};

    public static void main(String[] args) {
        TopKFrequent obj = new TopKFrequent();
        //方法一：排序实现
        obj.mySort(arr, 2);
        System.out.println();
        //方法二：使用优先队列
        obj.heapSort(arr, 2);
    }

    private void mySort(Integer[] arr, int k) {
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
                System.out.print(entry.getKey() + " ");
                count++;
            }
        }
    }

    private void heapSort(Integer[] arr, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        PriorityQueue<Freq> queue = new PriorityQueue<>();
        for (int key : map.keySet()) {
            queue.add(new Freq(key, map.get(key)));
        }

        LinkedList<Integer> list = new LinkedList<>();
        for (Freq freq : queue) {
            if (list.size() < k) {
                list.add(freq.e);
            }
        }
        System.out.println(list);
    }

    private class Freq implements Comparable<Freq> {
        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) return 1;
            else if (this.freq > another.freq) return -1;
            else return 0;
        }
    }

}

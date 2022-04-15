package com.code.algorithm.leetCode;

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
        System.out.println(Arrays.toString(obj.mySort(arr, 2)));
        //方法二：使用优先队列
        System.out.println(Arrays.toString(obj.heapSort(arr, 2)));
    }

    private int[] mySort(Integer[] arr, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                //逆序
                return o2.getValue() - o1.getValue();
            }
        });
        int[] array = new int[k];
        for (int i = 0; i < k; i++) {
            array[i] = list.get(i).getKey();
        }
        return array;
    }

    private int[] heapSort(Integer[] arr, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int key : map.keySet()) {
            queue.add(new Node(key, map.get(key)));
        }

        int[] array = new int[k];
        for (int i = 0; i < k; i++) {
            Node node = queue.poll();
            array[i] = node.e;
        }
        return array;
    }

    private class Node implements Comparable<Node> {
        public int e, freq;

        public Node(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Node node) {
            return node.freq - this.freq;
        }
    }

}

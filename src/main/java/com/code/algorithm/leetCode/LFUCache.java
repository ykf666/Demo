package com.code.algorithm.leetCode;

import java.util.*;

/**
 * case 460
 * LFU（Least frequently used）最不常使用
 * Created by yankefei on 2022/3/24.
 */
public class LFUCache {

    private int capacity;
    private int index;

    private Map<Integer, Node> map;
    private PriorityQueue<Node> queue;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.queue = new PriorityQueue();
    }

    public int get(int key) {
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            queue.remove(node);
            node.incrementCount(++this.index);
            queue.add(node);
            return node.getValue();
        }
        return -1;
    }

    public void put(int key, int value) {
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            if (node.getValue() != value) {
                node.setValue(value);
            }
            node.incrementCount(++this.index);
            this.queue.remove(node);
            this.queue.offer(node);
        } else {
            //new item
            if (this.capacity == this.map.size() && this.map.size() != 0) {
                Node nodeDel = this.queue.poll();
                this.map.remove(nodeDel.getKey());
            }
            if (this.capacity > 0) {
                Node node = new Node(key, value, ++this.index);
                this.map.put(key, node);
                this.queue.offer(node);
            }
        }
    }

    protected static class Node implements Comparable<Node> {

        private Integer key;
        private Integer value;
        private int index;
        private int count;

        public Node(int key, int value, int index) {
            this.key = key;
            this.value = value;
            this.index = index;
            this.count = 1;
        }

        public Integer getKey() {
            return key;
        }

        public void incrementCount(int index) {
            this.count++;
            this.index = index;
        }

        public Integer getValue() {
            return this.value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public int getCount() {
            return count;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public String toString() {
            return "{" + key + "=" + value +
                    ", index=" + index +
                    ", count=" + count +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            int c = this.count - o.getCount();
            if (c == 0) {
                //根据进入顺序排
                c = this.index - o.getIndex();
            }
            return c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public void show() {
        System.out.println(Arrays.toString(this.map.values().toArray()));
        System.out.println(Arrays.toString(this.queue.toArray()));
        System.out.println("-------------------------------------");
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(2, 1);
        lfuCache.show();

        lfuCache.put(3, 2);
        lfuCache.show();

        System.out.println(lfuCache.get(3));
        lfuCache.show();

        System.out.println(lfuCache.get(2));
        lfuCache.show();

        lfuCache.put(4, 3);
        lfuCache.show();

        System.out.println(lfuCache.get(2));
        lfuCache.show();

        System.out.println(lfuCache.get(3));
        lfuCache.show();

        System.out.println(lfuCache.get(4));
        lfuCache.show();

    }
}

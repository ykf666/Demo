package com.code.basic.Collections;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 优先级队列
 * Created by yankefei on 2022/3/24.
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        Node n1 = new Node(20);
        queue.add(n1);
        System.out.println(Arrays.toString(queue.toArray()));

        Node n2 = new Node(6);
        queue.add(n2);
        System.out.println(Arrays.toString(queue.toArray()));

        Node n3 = new Node(12);
        queue.add(n3);
        System.out.println(Arrays.toString(queue.toArray()));

        Node n4 = new Node(4);
        queue.add(n4);
        System.out.println(Arrays.toString(queue.toArray()));
    }

    private static class Node implements Comparable<Node> {

        private int value;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "" + value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.getValue();
        }
    }
}

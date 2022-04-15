package com.code.algorithm.leetCode;

import java.util.*;

/**
 * case 146
 * Least recently used（最近最少使用）
 * 自定义链表结构实现
 * Created by yankefei on 2022/3/21.
 */
public class LRUCache2 {

    private Map<Integer, Node> map;

    private int capacity;

    //earliest
    private Node head;
    //lastest
    private Node tail;

    private int size;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public Integer get(int key) {
        int value = -1;
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            if (node != null) {
                value = node.getValue();
                //move to last
                this.moveToLast(node);
            }
        }
        return value;
    }

    private void moveToLast(Node node) {
        //空链表
        if (this.head == null && this.tail == null) {
            this.head = this.tail = node;
            return;
        }
        //尾节点返回
        if (node.equals(this.tail)) {
            return;
        }
        //头节点
        if (node.equals(this.head)) {
            this.head.getNext().setPre(null);
            this.head = node.getNext();
        } else {
            node.getNext().setPre(node.getPre());
            node.getPre().setNext(node.getNext());
        }
        this.tail.setNext(node);
        node.setPre(this.tail);
        node.setNext(null);
        this.tail = node;
    }

    public void put(Integer key, Integer value) {
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            if (value != node.getValue()) {
                node.setValue(value);
            }
            this.moveToLast(node);
        } else {
            if (this.size != 0 && this.size == this.capacity) {
                //remove head
                Node n = this.head;
                this.map.remove(n.getKey());
                if (n.equals(this.tail)){
                    this.head = this.tail = null;
                } else {
                    this.head.setPre(null);
                    this.head = n.getNext();
                }
                n = null;
                this.size--;
            }
            //add node
            Node newNode = new Node(key, value);
            this.map.put(key, newNode);
            Node t = this.tail;
            if (t != null) {
                t.setNext(newNode);
                newNode.setPre(t);
                this.tail = newNode;
            } else {
                this.head = this.tail = newNode;
            }
            this.size++;
        }
    }

    private static class Node {

        private Node pre;
        private Node next;

        private Integer key;

        private Integer value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(key, node.key) && Objects.equals(value, node.value);
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }
    }

    public void show() {
        System.out.print("size=" + this.size + " [");
        Node n = this.head;
        for (; ; ) {
            if (n != null) {
                System.out.print(n.toString());
                n = n.getNext();
                if (n != null) {
                    System.out.print(", ");
                }
            } else {
                System.out.print("]");
                break;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache2 lruCache = new LRUCache2(1);
        lruCache.put(6, 8);
        lruCache.show();
        lruCache.put(12, 1);
        lruCache.show();

        lruCache.get(2);
        lruCache.show();

        lruCache.put(15, 11);
        lruCache.show();
        lruCache.put(5, 2);
        lruCache.show();
        lruCache.put(1, 15);
        lruCache.show();
        lruCache.put(4, 2);
        lruCache.show();

        lruCache.get(5);
        lruCache.show();

        lruCache.put(15, 15);
        lruCache.show();
    }

}

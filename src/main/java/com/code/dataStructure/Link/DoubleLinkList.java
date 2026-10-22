package com.code.dataStructure.Link;

/**
 * 双向链表实现
 * Created by yankefei on 2024/12/1.
 */
public class DoubleLinkList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoubleLinkList() {
        this.head = null;
        this.tail = null;
    }

    public void addFirst(T data){
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.size++;
    }

    public void addLast(T data) {
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(T data) {
        Node currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.data.equals(data)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public void clear() {
        Node currentNode = this.head;
        while (currentNode != null) {
            Node nextNode = currentNode.next;
            currentNode.prev = null;
            currentNode.next = null;

            currentNode = nextNode;
        }
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void print() {
        Node currentNode = this.head;
        while (currentNode != null) {
            System.out.print(currentNode.data + "  ");
            currentNode = currentNode.next;
        }
    }

    public void printReverse() {
        Node currentNode = this.tail;
        while (currentNode != null) {
            System.out.print(currentNode.data + "  ");
            currentNode = currentNode.prev;
        }
    }

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }

    }

    public static void main(String[] args) {
        DoubleLinkList myLinkList = new DoubleLinkList();
        myLinkList.addLast(35);
        myLinkList.addLast(10);
        myLinkList.addLast(45);
        myLinkList.addLast(44);
        myLinkList.addLast(90);
        myLinkList.addFirst(18);
        myLinkList.print();
        System.out.println();
        myLinkList.printReverse();
    }

}

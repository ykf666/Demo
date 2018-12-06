package com.code.demo.Tree;

/**
 * Created by yankefei on 2018/12/6.
 *
 * 树结点
 */
public class Node<E extends Comparable<E>> {

    E value;
    Node<E> left;
    Node<E> right;

    public Node(E value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

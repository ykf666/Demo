package com.code.dataStructure.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by yankefei on 2018/12/6.
 * <p>
 * 二叉查找树，使用先序遍历构建
 */
public class BinarySortTree<E extends Comparable<E>> {

    private Node<E> root;

    BinarySortTree() {
        this.root = null;
    }

    public BinarySortTree(Node root) {
        this.root = root;
    }

    public Node<E> getRoot() {
        return root;
    }

    public void insertNode(E value) {
        if (root == null) {
            root = new Node<>(value, null);
            return;
        }
        Node<E> currentNode = root;
        while (true) {
            if (value.compareTo(currentNode.value) > 0) {
                if (currentNode.right == null) {
                    currentNode.right = new Node<>(value, currentNode);
                    break;
                }
                currentNode = currentNode.right;
            } else {
                if (currentNode.left == null) {
                    currentNode.left = new Node<>(value, currentNode);
                    break;
                }
                currentNode = currentNode.left;
            }
        }
    }

    //先序遍历（递归）
    public void preOrderTraverse(Node<E> node) {
        System.out.print(node.value + " ");
        if (node.left != null)
            preOrderTraverse(node.left);
        if (node.right != null)
            preOrderTraverse(node.right);
    }

    //中序遍历（递归）
    public void inOrderTraverse(Node<E> node) {
        if (node.left != null) {
            inOrderTraverse(node.left);
        }
        System.out.print(node.value + " ");
        if (node.right != null) {
            inOrderTraverse(node.right);
        }
    }

    //后序遍历（递归）
    public void postOrderTraverse(Node<E> node) {
        if (node.left != null)
            postOrderTraverse(node.left);
        if (node.right != null)
            postOrderTraverse(node.right);
        System.out.print(node.value + " ");
    }

    //广度优先算法遍历二叉树，采用队列
    public void breadthFirstTraverse(Node<E> root) {
        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        Node<E> currentNode = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            System.out.print(currentNode.value + " ");
            if (currentNode.left != null)
                queue.offer(currentNode.left);
            if (currentNode.right != null)
                queue.offer(currentNode.right);
        }
    }

    //先序遍历（非递归）
    public void preOrderTraverse2() {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> currentNode = null;
        stack.push(this.root);
        while (!stack.isEmpty()) {
            currentNode = stack.pop();
            System.out.print(currentNode.value + " ");
            if (currentNode.right != null)
                stack.push(currentNode.right);
            if (currentNode.left != null)
                stack.push(currentNode.left);
        }
    }

    //中序遍历（非递归）
    public void inOrderTraverse2() {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> currentNode = this.root;
        while (currentNode != null || !stack.isEmpty()) {
            // 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.right;
        }
    }

    //后序遍历（非递归）
    public void postOrderTraverse2() {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> currentNode = this.root;
        Node<E> rightNode = null;
        while (currentNode != null || !stack.isEmpty()) {
            // 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            // 当前结点没有右结点或上一个结点（已经输出的结点）是当前结点的右结点，则输出当前结点
            while (currentNode.right == null || currentNode.right == rightNode) {
                System.out.print(currentNode.value + " ");
                rightNode = currentNode;
                if (stack.isEmpty()) {
                    return; //root以输出，则遍历结束
                }
                currentNode = stack.pop();
            }
            stack.push(currentNode); //还有右结点没有遍历
            currentNode = currentNode.right;
        }
    }

    public Node<E> findLastCommonParent(Node<E> n1, Node<E> n2, Node<E> currentNode) {
        Integer currentVal = (Integer) currentNode.value;
        if ((((Integer) n1.value) < currentVal) && (((Integer) n2.value) < currentVal)) {
            currentNode = findLastCommonParent(n1, n2, currentNode.left);
        }
        if ((((Integer) n1.value) > currentVal) && (((Integer) n2.value) > currentVal)) {
            currentNode = findLastCommonParent(n1, n2, currentNode.right);
        }
        return currentNode;
    }

    //找到中序遍历中下一个节点值
    public static Node findInOrderNextNode(Node node) {
        if (node == null) {
            return null;
        }
        Node next = null;
        if (node.right != null) {
            Node rightNode = node.right;
            while (rightNode.left != null) {
                rightNode = rightNode.left;
            }
            next = rightNode;
        } else if (node.left != null) {

        }
        return null;
    }

    /**
     * 树结点
     */
    public static class Node<E extends Comparable<E>> {
        E value;
        public Node<E> left;
        public Node<E> right;
        Node<E> parent;

        public Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node(E value, Node parent) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = parent;
        }

        public String getValue() {
            return this.value.toString();
        }

        @Override
        public String toString() {
            return "Node={" +
                    "value=" + value +
                    ", left=" + (left == null ? "" : left.value) +
                    ", right=" + (right == null ? "" : right.value) +
                    ", parent=" + (parent == null ? "" : parent.value) +
                    '}';
        }
    }

}

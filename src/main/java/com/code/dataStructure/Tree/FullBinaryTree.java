package com.code.dataStructure.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 满二叉树
 * Created by yankefei on 2024/11/30.
 */
public class FullBinaryTree<T> {

    private static class Node<T> {
        T value;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        public Node() {
            this.value = null;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node(T value, Node<T> parent) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }
    }

    private Node<T> root;

//    public void add(T value) {
//        if (this.root == null) {
//            this.root = new Node<>(value, null);
//        } else {
//            Node<T> currentNode = root;
//            while (currentNode != null) {
//                if (currentNode.left == null) {
//                    currentNode.left = new Node<>(value, currentNode);
//                } else if (currentNode.right == null) {
//                    currentNode.right = new Node<>(value, currentNode);
//                } else {
//                    currentNode = currentNode.left;
//                }
//            }
//        }
//    }

    public void add(T value) {
        if (this.root == null) {
            this.root = new Node<T>(value, null);
            return;
        }
        Node current = root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(current);

        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.left == null) {
                current.left = new Node(value, current);
                return;
            } else {
                queue.offer(current.left);
            }
            if (current.right == null) {
                current.right = new Node(value, current);
                return;
            } else {
                queue.offer(current.right);
            }
        }
    }

    public void addRecursion(T value) {
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }
        addRecursion(value, this.root);
    }

    private void addRecursion(T value, Node<T> currentNode) {
        if (currentNode.left == null) {
            currentNode.left = new Node<>(value, currentNode);
        } else if (currentNode.right == null) {
            currentNode.right = new Node<>(value, currentNode);
        } else {
            addRecursion(value, currentNode.left);
        }
    }

    public void preOrder() {
        preOrderTraverse(this.root);
        System.out.println();
    }

    private void preOrderTraverse(Node<T> currentNode) {
        if (currentNode != null) {
            System.out.print(currentNode.value + " ");
            preOrderTraverse(currentNode.left);
            preOrderTraverse(currentNode.right);
        }
    }

    public void middleOrder() {
        middleOrderTraverse(this.root);
        System.out.println();
    }

    private void middleOrderTraverse(Node<T> currentNode) {
        if (currentNode != null) {
            middleOrderTraverse(currentNode.left);
            System.out.print(currentNode.value + " ");
            middleOrderTraverse(currentNode.right);
        }
    }

    public void postOrder() {
        postOrderTraverse(this.root);
        System.out.println();
    }

    private void postOrderTraverse(Node<T> currentNode) {
        if (currentNode != null) {
            postOrderTraverse(currentNode.left);
            postOrderTraverse(currentNode.right);
            System.out.print(currentNode.value + " ");
        }
    }

    /**
     * 根据树高构建一棵树
     *
     * @param n
     */
    public Node<T> build(int n) {
        return build(n, null);
    }

    private Node<T> build(int n, Node<T> parent) {
        if (n <= 0) {
            return null;
        }
        Node<T> node = new Node<T>();
        node.parent = parent;
        node.left = build(n - 1, node);
        node.right = build(n - 1, node);
        return node;
    }

    public static void main(String[] args) {
        FullBinaryTree<Integer> tree = new FullBinaryTree<>();
        tree.add(11);
        tree.add(65);
        tree.add(48);
        tree.add(10);
        tree.add(59);
        tree.add(23);
        tree.add(60);
        tree.preOrder();
        tree.middleOrder();
        tree.postOrder();
    }

}


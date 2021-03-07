package com.code.basic.algorithm.offers;

import com.code.dataStructure.Tree.BinarySortTree;

/**
 * 输入一个二叉树的前序遍历结果和中序遍历结果，重建该二叉树
 * 假设输入的结果中都不包含重复值，例如{1, 2, 4, 7, 3, 5, 6, 8}和{4, 7, 2, 1, 5, 3, 8, 6}，重建该树并输出根节点
 * Created by yankefei on 2020/5/2.
 */
public class Case7 {

    static int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
    static int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};

    public static void main(String[] args) {
        BinarySortTree.Node root = construct(preOrder, inOrder, preOrder.length);
        BinarySortTree tree = new BinarySortTree(root);
        tree.preOrderTraverse(tree.getRoot());
        System.out.println();
        tree.inOrderTraverse(tree.getRoot());
        System.out.println();
        tree.postOrderTraverse(tree.getRoot());
    }

    private static BinarySortTree.Node<Integer> construct(int[] preOrder, int[] inOrder, int length) {
        if (preOrder == null || inOrder == null || length <= 0 || preOrder.length != inOrder.length) {
            return null;
        }
        return constructCore(0, preOrder.length - 1, 0, inOrder.length - 1);
    }

    private static BinarySortTree.Node<Integer> constructCore(int startPreOrder, int endPreOrder, int startInOrder, int endInOrder) {
        int rootValue = preOrder[startPreOrder];
        BinarySortTree.Node rootNode = new BinarySortTree.Node(rootValue);
        rootNode.left = rootNode.right = null;
        if (startInOrder == endPreOrder) {
            if (startInOrder == endInOrder && preOrder[startPreOrder] == inOrder[startInOrder]) {
                return rootNode;
            } else {
                throw new RuntimeException("invalid input");
            }
        }
        //在中序遍历中找到根节点
        int rootInOrder = startInOrder;
        while (rootInOrder <= endInOrder && inOrder[rootInOrder] != rootValue) {
            rootInOrder++;
        }
        if (rootInOrder == endInOrder && inOrder[rootInOrder] != rootValue) {
            throw new RuntimeException("invalid input");
        }
        int leftTreeLength = rootInOrder - startInOrder;
        int leftPreOrderEnd = startPreOrder + leftTreeLength;
        if (leftTreeLength > 0) {
            //构建左子树
            rootNode.left = constructCore(startPreOrder + 1, leftPreOrderEnd, startInOrder, rootInOrder - 1);
        }
        if (leftTreeLength < endPreOrder - startPreOrder) {
            //构建右子树
            rootNode.right = constructCore(leftPreOrderEnd + 1, endPreOrder, rootInOrder + 1, endInOrder);
        }
        return rootNode;
    }


}

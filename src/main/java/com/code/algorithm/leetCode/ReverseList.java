package com.code.algorithm.leetCode;

/**
 * 反转链表
 * Created by yankefei on 2022/4/8.
 */
public class ReverseList {

    public static void main(String[] args) {
        ReverseBetween.ListNode head = new ReverseBetween.ListNode(1);
        ReverseBetween.ListNode n2 = new ReverseBetween.ListNode(2);
        ReverseBetween.ListNode n3 = new ReverseBetween.ListNode(3);
        ReverseBetween.ListNode n4 = new ReverseBetween.ListNode(4);
        ReverseBetween.ListNode n5 = new ReverseBetween.ListNode(5);

        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        printList(head);
        ReverseBetween.ListNode newHead = reverseList(head);
        printList(newHead);
    }

    public static ReverseBetween.ListNode reverseList(ReverseBetween.ListNode head) {
        ReverseBetween.ListNode dummyNode = new ReverseBetween.ListNode(0);

        ReverseBetween.ListNode node = head;
        while (node != null) {
            //临时节点
            ReverseBetween.ListNode next = node.next;
            node.next = dummyNode.next;
            dummyNode.next = node;
            node = next;
        }
        return dummyNode.next;
    }

    public static void printList(ReverseBetween.ListNode head) {
        ReverseBetween.ListNode node = head;
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println();
    }

}

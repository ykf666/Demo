package com.code.offers;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 从尾到头打印链表
 * Created by yankefei on 2020/4/26.
 */
public class Case6 {

    private final static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //方式一：使用栈
        Stack stack = new Stack();
        for (int i = 0; i < list.size(); i++) {
            stack.push(list.get(i));
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
        //方式二：使用递归
    }

}


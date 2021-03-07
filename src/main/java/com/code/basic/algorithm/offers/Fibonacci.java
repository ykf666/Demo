package com.code.basic.algorithm.offers;

import java.util.Arrays;

/**
 * 斐波那契数列
 * Created by yankefei on 2020/5/9.
 */
public class Fibonacci {

    private static int count = 0;
    private static long a = 0;

    public static void main(String[] args) {
        int n = 10;
        getFibonacciArray(n);
        getFibonacciArrayRecursion(n);
        System.out.println("栈调用深度：" + count);
        try {
            //结合-Xss128k参数使用
            //The stack size specified is too small, Specify at least 108k
            kk();
        } catch (StackOverflowError e) {
            System.out.println(e.toString() + "，递归深度: " + a);
        }
    }

    private static void kk() {
        a++;
        kk();
    }

    private static int[] getFibonacciArray(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    //递归实现
    private static int recursion(int n) {
        count++;
        if (n == 1) return 1;
        if (n == 2) return 1;
        return recursion(n - 1) + recursion(n - 2);
    }

    private static int[] getFibonacciArrayRecursion(int n) {
        if (n <= 0) {
            throw new RuntimeException("invalid input");
        }
        int[] arr = new int[n];
        long sTime = System.currentTimeMillis();
        for (int i = 1; i <= arr.length; i++) {
            arr[i - 1] = recursion(i);
        }
        long eTime = System.currentTimeMillis();
        System.out.println("递归调用耗时：" + (eTime - sTime));
        System.out.println(Arrays.toString(arr));
        return arr;
    }
}
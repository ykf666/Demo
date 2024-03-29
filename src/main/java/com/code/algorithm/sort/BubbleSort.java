package com.code.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序算法
 * <p>
 * 基本思想：相邻两元素比较，或大或小则交换位置，一趟之后，最小或最大的值就会交换到第一个或最后一个位置
 *
 * @author yan.kefei
 * @date 2017/9/15 17:22
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr1 = {6, 2, -5, 10, 104, 12, 92, 87};
        int[] arr2 = {6, 2, -5, 10, 104, 12, 92, 87};
        sort1(arr1);
        System.out.println();
        sort2(arr2);
    }

    public static void sort1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void sort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}

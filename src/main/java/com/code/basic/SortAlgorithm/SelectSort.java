package com.code.basic.SortAlgorithm;

import java.util.Arrays;

/**
 * 选择排序算法(简单排序算法)
 *
 * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
 * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
 *
 * @author yan.kefei
 * @date 2017年3月18日 下午7:54:36
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {6, 2, -5, 10, 104, 12, 92, 87};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int temp;
                if (arr[j] < arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}

package com.code.demo.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {2, -5, 33, 10, 4, 102, 92, 87};
        quick_sort(arr, 0, arr.length - 1);
        System.out.print(Arrays.toString(arr));
    }

    private static void quick_sort(int[] arr, int left, int right) {
        //结束迭代
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;

        //设置基准值，将最左端元素作为基准值
        int temp = arr[left];

        while (i != j) {
            //往左移位，直到大于temp
            while (i < j && arr[j] >= temp) {
                j--;
            }
            //往右移位，直到小于temp
            while (i < j && arr[i] <= temp) {
                i++;
            }
            if (i < j) {
                //交换彼此的数据
                int tt = arr[i];
                arr[i] = arr[j];
                arr[j] = tt;
            }

        }

        //交换基位数据
        int kk = arr[i];
        arr[i] = temp;
        arr[left] = kk;

        //下一次迭代
        quick_sort(arr, left, i - 1);//左半边
        quick_sort(arr, j + 1, right);//右半边
    }
}

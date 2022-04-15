package com.code.algorithm.sort;

import java.util.Arrays;

/**
 * Created by yankefei on 2018/12/10.
 * <p>
 * 归并排序
 * 核心思想是将两个有序的数列合并成一个大的有序的序列。通过递归，层层合并，即为归并。
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {6, 92, 2, -5, 10, 104, 12, 92, 87};
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //递归拆分左侧数组
            sort(arr, left, mid, temp);
            //递归拆分右侧数组
            sort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);

            System.out.println(Arrays.toString(arr));
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        //将左边剩余元素加入temp
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        //将temp中的元素全部拷贝到原数组中
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

}

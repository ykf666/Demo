package com.code.demo.SortAlgorithm;

import java.util.Arrays;

/**
 * Created by yankefei on 2018/12/10.
 * <p>
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {6, 2, -5, 10, 104, 12, 92, 87};
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
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

        for (int k = 0; k < arr.length; k++) {
            arr[k] = temp[k];
        }
        System.out.println(Arrays.toString(temp));
    }

}

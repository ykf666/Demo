package com.code.demo.SortAlgorithm;

import java.util.Arrays;

/**
 * 插入排序算法之直接插入排序
 *
 * 基本思想：在要排序的一组数中，假设前面(n-1)[n>=2]个数已经是排好顺序的，现在要把第n个数插到前面的有序数中
 *
 * @author yan.kefei
 * @date 2017/9/19 22:25
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {66, 22, 10, 104, 12, -5, 92, 87};
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];//待排组的第一个元素值
            int j = i - 1;//有序组的最后一个元素下标
            while (j >= 0 && temp < arr[j]) {
                //若不是合适位置，有序元素向后移动
                arr[j + 1] = arr[j];
                j--;
            }
            //找到合适位置，将元素插入
            arr[j + 1] = temp;
            System.out.println(Arrays.toString(arr));
        }
    }
}
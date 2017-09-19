package com.code.demo.sort;

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
        int[] arr = {6, 2, -5, 10, 104, 12, 92, 87};
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (int j = i; j > 0; j--) {

            }

        }
//        int temp;
//        for (int i = 1; i < arr.length; i++) {
//            int j = i - 1;
//            temp = arr[i];
//            for (; j >= 0 && temp < arr[j]; j--) {
//                //将大于temp的值整体后移一个单位
//                arr[j + 1] = arr[j];
//            }
//            arr[j + 1] = temp;
//        }
        System.out.println(Arrays.toString(arr));
    }
}

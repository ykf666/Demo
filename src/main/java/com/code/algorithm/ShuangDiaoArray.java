package com.code.algorithm;

/**
 * 给定一个数组，保证数组中值的顺序是递增或者递减，找出最小值
 * Created by yankefei on 2022/4/8.
 */
public class ShuangDiaoArray {

    public static void main(String[] args) {
        int[] arr = new int[]{200, 100, 99, 24, 7, 9, 10, 100, 200, 400, 500, 600};
        Integer result = find(arr, 0, arr.length - 1);
        System.out.println(result);
    }

    private static Integer find(int[] array, int left, int right) {
        if (left == right) {
            return array[left];
        }
        int mid = (left + right) / 2;
        if (mid == 0 || mid == array.length - 1) {
            return array[mid];
        }
        int t = array[mid];
        Integer result = null;
        if (t < array[mid - 1] && t < array[mid + 1]) {
            return array[mid];
        }
        if (t < array[mid - 1] && t > array[mid + 1]) {
            result = find(array, mid + 1, right);
        }
        if (t > array[mid - 1] && t < array[mid + 1]) {
            result = find(array, left, mid - 1);
        }
        return result;
    }

}

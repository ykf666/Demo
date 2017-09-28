package com.code.demo.sort;

import java.util.Arrays;

/**
 * 快速排序算法
 *
 * 基本思想：
 * 1、以一个数为基准数，一般为第一个数
 * 2、分治法，把数组中比基准大的放右边，比基准小的放左边
 * 3、递归排序子数组
 *
 * 具体实现：
 * 1、从右边开始遍历，直到比基准小的数停止，然后从左边遍历，直到比基准大的数停止，如果此时数组仍没有遍历结束，则先交换两个循环遍历结束位置的值
 * 2、继续遍历，直到数组遍历完成，即两边遍历的位置交叉，把基准数放置到此处，此处左边都是比基准数小的值，右边都比它大
 * 3、此时，数组被划分为两个子数组，则递归子数组排序
 *
 * @author yan.kefei
 * @date 2017年9月28日 下午10:31:02
 */
public class QuickSort2 {

    public static void main(String[] args) {
        int[] arr = {66, 25, 33, 10, 104, 12, 92, 87};
        quick_sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quick_sort(int[] arr, int left, int right) {
        //结束迭代
        if (left > right) {
            return;
        }
        int low = left;
        int high = right;

        //设置基准值，将最左端元素作为基准值
        int base = arr[left];
        while (low < high) {
            //往左移位，直到小于base
            while (low < high && arr[high] > base) {
                high--;
            }

            if (low < high) {
                arr[low] = arr[high];
                low++;
            }

            //往右移位，直到大于base
            while (low < high && arr[low] < base) {
                low++;
            }

            if (low < high) {
                arr[high] = arr[low];
            }
        }
        arr[low] = base;

        System.out.println(Arrays.toString(arr));
        //下一次迭代
        quick_sort(arr, left, low - 1);//左半边
        quick_sort(arr, high + 1, right);//右半边
    }
}

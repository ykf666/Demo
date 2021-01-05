package com.code.basic.SortAlgorithm;

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
 * @date 2017年9月14日 下午10:45:02
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {6, 2, -5, 10, 104, 12, 92, 87};
        quick_sort(arr, 0, arr.length - 1);
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

        while (low != high) {
            //往左移位，直到小于base
            while (low < high && arr[high] >= base) {
                high--;
            }

            //往右移位，直到大于base
            while (low < high && arr[low] <= base) {
                low++;
            }

            if (low < high) {
                //交换彼此的数据
                int tt = arr[low];
                arr[low] = arr[high];
                arr[high] = tt;
            }
        }

        //交换基位数据
        int kk = arr[low];
        arr[low] =  base;
        arr[left] = kk;

        System.out.println(Arrays.toString(arr));
        //下一次迭代
        quick_sort(arr, left, low - 1);//左半边
        quick_sort(arr, high + 1, right);//右半边
    }
}

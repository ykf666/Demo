package com.code.demo.sort;

import java.util.Arrays;

/**
 * @author yan.kefei
 * @date 2018/1/25 16:56
 */
public class Heap {

    /**
     * <b>Description:</b>保持堆的特性，称之为堆化<br>
     *
     * @param arr
     *            当前堆
     * @param n
     *            当前堆大小
     * @param i
     *            i结点
     * @Note <b>Author:</b> yankefei <br>
     *       <b>Date:</b> 2018年1月10日 上午10:50:43
     */
    private void Heapify(int arr[], int n, int i) {
        // 左子结点
        int LEFT = 2 * i;
        // 右子结点
        int RIGHT = 2 * i + 1;
        // 当前堆的元素数目
        int HEAP_SIZE = i;
        if (LEFT <= n) {
            HEAP_SIZE = arr[LEFT - 1] > arr[i - 1] ? LEFT : HEAP_SIZE;
        }
        if (RIGHT <= n) {
            // 从i,2*i,2*i+1中找出最大的一个
            HEAP_SIZE = arr[RIGHT - 1] > arr[HEAP_SIZE - 1] ? RIGHT : HEAP_SIZE;
        }
        // i不是最大的
        if (HEAP_SIZE != i) {
            swap(arr, HEAP_SIZE, i);
            // 交换后，子树有可能违反最大堆性质
            Heapify(arr, n, HEAP_SIZE);
        }
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a - 1];
        arr[a - 1] = arr[b - 1];
        arr[b - 1] = tmp;
    }

    // 建堆，初始化堆
    public void BuildHeap(int arr[]) {
        for (int i = arr.length / 2; i >= 1; i--) {
            Heapify(arr, arr.length, i);
        }
    }

    // 从堆中取出最大值，n为堆大小
    public int delete(int[] arr, int n) {
        int max = arr[0];
        arr[0] = arr[n - 1];
        n--;
        Heapify(arr, n, 1);
        return max;
    }

    // 向堆中添加元素
    public int[] insert(int[] arr, int n, int val) {
        int[] arr2 = Arrays.copyOf(arr, n + 1);
        arr2[n] = val;
        // 插入元素的当前位置(从1开始)
        int t = n + 1;
        int PARENT = t / 2;
        while (PARENT >= 1 && arr[PARENT] < val) {
            swap(arr2, PARENT, t);
            t = PARENT;
            PARENT = t / 2;
        }
        return arr2;
    }

    public static void main(String[] args) {
        int array[] = {19, 28, 17, 41, 15, 16, 62, 22, 30, 13};
        // 建堆，初始化
        Heap heap = new Heap();
        heap.BuildHeap(array);
        System.out.println(Arrays.toString(array));

        // 添加新元素
        int[] tmp = heap.insert(array, array.length, 100);
        System.out.println(Arrays.toString(tmp));

        // 取出堆中最大值
        heap.delete(tmp, tmp.length);
        int[] tmp1 = Arrays.copyOf(tmp, tmp.length - 1);
        System.out.println(Arrays.toString(tmp1));

        heap.delete(tmp1, tmp1.length);
        int[] tmp2 = Arrays.copyOf(tmp1, tmp1.length - 1);
        System.out.println(Arrays.toString(tmp2));
    }

}

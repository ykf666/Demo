package com.code.basic.algorithm.sort;

import java.util.Arrays;

/**
 * Created by yankefei on 2018/12/10.
 * <p>
 * 希尔排序：
 * 属于插入排序算法，直接插入排序算法在在本身数量比较少的时候情况下效率很高，如果待排数的数量很多，其效率不是很理想。
 * 我们不希望它是一步一步的移动，而是大步大步的移动。希尔排序就被发明出来了，它也是当时打破效率 O（n2）的算法之一。
 * <p>
 * 主要思想：先将整个待排记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录"基本有序"时，在对全体进行一次直接插入排序。
 * <p>
 * 原理：希尔排序算法通过设置一个间隔，对同样间隔的数的集合进行插入排序，
 * 此数集合中的元素移位的长度是以间隔的长度为准，这样就实现了大步位移。
 * 但是最后需要对元素集合进行一次直接插入排序，所以最后的间隔一定是1。
 */
public class HillSort {

    public static void main(String[] args) {
        int[] arr = {6, 2, -5, 10, 104, 12, 92, 87};

        //间隔大小
        int increment = arr.length;
        while (increment > 1) {
            increment = increment / 3 + 1;
            for (int i = increment; i < arr.length; i += increment) {
                //插入排序
                int temp = arr[i];   //待排序第一个数
                int j = i - increment;  //已排序数组的最后一个元素位置
                while (j >= 0 && temp < arr[j]) {
                    arr[i] = arr[j];
                    j -= increment;
                }
                arr[j + increment] = temp;
            }
            System.out.println(Arrays.toString(arr));
        }
    }

}

package com.code.offers;

import java.util.Arrays;

/**
 * Created by yankefei on 2020/5/9.
 */
public class MaxSumSubArray {

    private static int recursionCount = 0;

    public static void main(String[] args) {
        int[] arr = {-2, 11, -4, 13, -5, -2};
//        int[] arr = {-3, -11, -4, -13, -5, -2};
//        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println("原始数组：" + Arrays.toString(arr));
        System.out.println("****************穷举法****************");
        subSequenceMaxSum_1(arr);
        System.out.println("****************穷举法优化****************");
        subSequenceMaxSum_2(arr);
        System.out.println("****************动态规划****************");
        subSequenceMaxSum_3(arr);
        System.out.println("****************动态规划（优化）****************");
        subSequenceMaxSum_4(arr);
        System.out.println("****************分治法****************");
        int max = subSequenceMaxSum_5(arr);
        System.out.println("最大子序列和：" + max);
        System.out.println("递归调用次数为：" + recursionCount);
    }

    //暴力穷举法，计算所有子序列和，记录最大值
    //时间复杂度O(n^3)
    private static void subSequenceMaxSum_1(int[] array) {
        int maxSum = array[0];
        int start = 0;
        int end = 0;
        int count = 0;
        int sum;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                sum = 0;
                for (int k = i; k <= j; k++) {
                    sum = sum + array[k];
                    count++;
                }
                if (sum > maxSum) {
                    maxSum = sum;
                    start = i;
                    end = j;
                }
            }
        }
        System.out.println("最大子序列和：" + maxSum);
        int len = end - start + 1;
        int[] subArray = new int[len];
        for (int m = 0; m < subArray.length; m++) {
            subArray[m] = array[start + m];
        }
        System.out.println("最大子序列为：" + Arrays.toString(subArray));
        System.out.println("循环次数：" + count);
    }

    //穷举法优化，时间复杂度为O(n^2)
    private static void subSequenceMaxSum_2(int[] array) {
        int maxSum = array[0];
        int sum;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
                count++;
            }
        }
        System.out.println("最大子序列和：" + maxSum);
        System.out.println("循环次数：" + count);
    }

    //动态规划，时间复杂度为O(n)
    private static void subSequenceMaxSum_3(int[] array) {
        int count = 0;
        int[] b = new int[array.length];
        b[0] = array[0];
        for (int i = 0; i < array.length - 1; i++) {
            b[i + 1] = b[i] + array[i + 1] > array[i + 1] ? b[i] + array[i + 1] : array[i + 1];
            count++;
        }
        //找出b数组中的最大值
        int sum = b[0];
        for (int j = 0; j < b.length; j++) {
            count++;
            if (b[j] > sum) {
                sum = b[j];
            }
        }
        System.out.println("最大子序列和：" + sum);
        System.out.println("循环次数：" + count);
    }

    //动态规划（优化）
    private static void subSequenceMaxSum_4(int[] array) {
        int maxSum = array[0];
        int sum = maxSum;
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            sum = sum + array[i] > array[i] ? sum + array[i] : array[i];
            if (sum > maxSum) {
                maxSum = sum;
            }
            count++;
        }
        System.out.println("最大子序列和：" + maxSum);
        System.out.println("循环次数为：" + count);
    }

    //分治法
    private static int subSequenceMaxSum_5(int[] array) {
        recursionCount++;
        if (array.length <= 1) {
            return array[0];
        }
        int middle = array.length / 2;
        int[] leftArray = Arrays.copyOfRange(array, 0, middle);
        int[] rightArray = Arrays.copyOfRange(array, middle, array.length);
        int leftMax = subSequenceMaxSum_5(leftArray);
        int leftMaxSum = array[0];
        int leftSum = 0;
        for (int i = 0; i < middle; i++) {
            leftSum += array[i];
            if (leftSum > leftMaxSum) {
                leftMaxSum = leftSum;
            }
        }
        int rightMax = subSequenceMaxSum_5(rightArray);
        int rightMaxSum = array[middle];
        int rightSum = 0;
        for (int i = middle; i < array.length; i++) {
            rightSum += array[i];
            if (rightSum > rightMaxSum) {
                rightMaxSum = rightSum;
            }
        }
        return max(leftMaxSum + rightMaxSum, leftMax, rightMax);
    }

    private static int max(int tempMax, int leftMax, int rightMax) {
        int max = tempMax;
        if (leftMax > max) {
            max = leftMax;
        }
        if (rightMax > max) {
            max = rightMax;
        }
        return max;
    }
}

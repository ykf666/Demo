package com.code.basic.algorithm.leetCode;

import java.util.HashMap;

/**
 * Created by yankefei on 2020/12/8.
 */
public class DP_523_checkSubArraySum {

    public static void main(String[] args) {
        int[] arr = {23, 2, 6, 4, 7};
        int k = 6;
        System.out.println(checkSubArraySum(arr, k));
    }

    private static boolean checkSubArraySum(int[] array, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //为何 map.put(0, -1) 呢？ 如果在第2位找到了mod == 0的数，那就 1 -（-1）>1，return true
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            // running sum
            sum += array[i];
            if (k != 0) {
                sum %= k;
            }
            // find sum % k is in the HashMap
            if (map.containsKey(sum)) {
                // subarray length at least two
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                // key: runnng sum -- value: index
                map.put(sum, i);
            }
        }
        return false;
    }
}

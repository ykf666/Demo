package com.code.algorithm.leetCode;

import java.util.Arrays;

/**
 * case 300
 * 最长递增子序列（动态规划）
 * <p>
 * Created by yankefei on 2022/4/1.
 */
public class LengthOfLIS {

    public static void main(String[] args) {
//        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = new int[]{4, 10, 4, 3, 8, 9};
        System.out.println("最长递增子序列长度：" + lengthOfLis(nums));
    }

    public static int lengthOfLis(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}

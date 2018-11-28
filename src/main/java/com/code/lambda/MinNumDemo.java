package com.code.lambda;

import java.util.stream.IntStream;

/**
 * Created by yankefei on 2018/11/27.
 */
public class MinNumDemo {

    public static void main(String[] args) {
        int[] nums = {22, 33, 44, -15, 3};
        int min = Integer.MAX_VALUE;

        for (int i : nums) {
            if (i < min) {
                min = i;
            }
        }

        System.out.println(min);

        //stream操作，如果数据量很大，可并行计算
     int min2 = IntStream.of(nums).parallel().min().getAsInt();
        System.out.println(min2);
    }
}

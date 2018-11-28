package com.code.stream;

import java.util.stream.IntStream;

/**
 * Created by yankefei on 2018/11/28.
 */
public class StreamDemo {

    public static void main(String[] args) {
        //外部迭代
        int[] nums = {7, 1, 20, 3};
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println(sum);

        //使用stream的内部迭代
        //这个map就是中间操作（即返回流的操作），sum就是终止操作
        int sum2 = IntStream.of(nums).map(StreamDemo::doubleInt).sum();
        System.out.println(sum2);

        System.out.println("惰性求值就是终止操作没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo::doubleInt);
    }

    public static int doubleInt(int k) {
        System.out.println(k + "执行了乘以2");
        return k * 2;
    }

}

package com.code.basic.algorithm.offers;

/**
 * 要求不能使用乘法/除法，以及if/else/while/for等方式
 * Created by yankefei on 2020/3/15.
 */
public class Sum_one2n {

    public static void main(String[] args) {
        System.out.println(sum_1(4));
        System.out.println(sum_2(4));
    }

    private static int sum_1(int n) {
        int sum = n;
        boolean result = (n > 0) && (sum += sum_1(n - 1)) > 0;
        return sum;
    }

    public static int sum_2(int n) {
        //n(n+1)/2
        int sum = ((int) (Math.pow(n, 2)) + n) >> 1;
        return sum;
    }
}
